package com.cloud.jack.app.distributed.redis;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.cloud.jack.app.config.RedisConfig;
import com.cloud.jack.app.test.distributed.redis.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CacheClientTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 解决缓存穿透问题
     * 1.将空值设置给Redis
     * 2.布隆过滤器
     */
    public Course queryWithPassThrough(String key){
        //从Redis缓存
        String json = stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(json)){
            return JSON.parseObject(json, Course.class);
        }
        //判断命中的是否是空对象
        if(json!= null){
            return null;
        }
        //不存在，从数据库查询
        Course course = null;
        if(course != null){
            //存入Redis缓存
            stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(course));
        }
        //将空值设置给Redis
        stringRedisTemplate.opsForValue().set(key, "", 3600, TimeUnit.SECONDS);
        return new Course();
    }

    public  String  queryWithPassThrough1(String key, Supplier<String> supplier,int seconds){
        String value = stringRedisTemplate.opsForValue().get(key);
        if(value != null){
            return value;
        }
        value = supplier.get();
        if (value != null){
            stringRedisTemplate.opsForValue().set(key,value,seconds,TimeUnit.SECONDS);
        }else {
            stringRedisTemplate.opsForValue().set(key,"NOT_FOUND",seconds,TimeUnit.SECONDS);
        }
        return value;
    }

    /**
     * 解决缓存击穿问题 通过互斥锁
     * @param key
     * @return
     */
    public Object getData(String key) {
        // 先从缓存中获取数据
        Object result = stringRedisTemplate.opsForValue().get(key);
        if (result == null) {
            // 如果缓存中没有数据，则需要使用分布式锁保证只有一个线程能够从数据库中获取数据
            //分布式锁实例创建
            RLock lock = redissonClient.getLock(key);
            try {
                if (lock.tryLock()) {
                    // 防止缓存穿透
                    result = stringRedisTemplate.opsForValue().get(key);
                    if (result == null) {
                        // 如果缓存中还是没有数据，则从数据库中获取数据
                        result = getFromDB(key);
                        // 将数据写入缓存中，并设置过期时间
                        if (result != null) {
                            stringRedisTemplate.opsForValue().set(key, result+"", 60);
                        } else {
                            // 如果数据库中也没有数据，则说明缓存穿透
                            // 可以将一个空值设置到缓存中，并设置较短的过期时间，避免缓存穿透问题
                            stringRedisTemplate.opsForValue().set(key, null, 10);
                        }
                    }
                }
            } finally {
                lock.unlock();
            }
        }
        return result;
    }

    /**
     * 解决缓存击穿问题 通过逻辑过期时间
     * @param key
     * @return
     */
    public Object getData1(String key) {
        // 先从缓存中获取数据
        Object result = stringRedisTemplate.opsForValue().get(key);
        if (result == null) {
            // 在进行数据库查询前先判断逻辑过期时间
            // 如果逻辑过期时间未过期，就直接返回数据，避免缓存击穿
            String logicalExpireTimeStr = stringRedisTemplate.opsForValue().get("logical_expire_" + key);
            if(StrUtil.isNotEmpty(logicalExpireTimeStr)){
                long logicalExpireTime = Long.parseLong(logicalExpireTimeStr);
                if (logicalExpireTime == 0 || logicalExpireTime > System.currentTimeMillis()) {
                    return null;
                }
            }

            // 创建分布式锁
            //分布式锁实例创建
            RLock lock = redissonClient.getLock(key);
            try {
                if (lock.tryLock()) {
                    // 再次尝试从缓存中获取数据
                    result = stringRedisTemplate.opsForValue().get(key);
                    if(result == null) {
                        // 从数据库中获取数据
                        result = getFromDB(key);
                        // 将获取的数据写入缓存中，并设置过期时间
                        if (result != null) {
                            stringRedisTemplate.opsForValue().set(key, result+"", 60);
                            stringRedisTemplate.opsForValue().set("logical_expire_" + key, result+"",System.currentTimeMillis() + 5 * 60 * 1000);
                        } else {
                            // 数据库中也没有数据，为了避免缓存穿透问题，将空值写入缓存并设置较短的过期时间
                            stringRedisTemplate.opsForValue().set(key, null, 10);
                        }
                    }
                } else {
                    // 未获取到分布式锁，等待一段时间后重试
                    Thread.sleep(1000); // 等待一段时间后再次尝试操作
                    getData(key);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock(); // 解锁
            }
        }
        return result;
    }

    private Object getFromDB(String key) {
        // 从数据库中获取数据
        return null;
    }

    public Object getData(String key, int retryCount){
        // 先从缓存中获取数据
        Object result = redisTemplate.opsForValue().get(key);
        if (result != null) {
            return result;
        }

        // 判断逻辑过期时间
        String logicalExpireKey = "logical_expire_" + key;
        Object obj = redisTemplate.opsForValue().get(logicalExpireKey);
        if (obj != null) {
            long logicalExpireTime = Long.parseLong(obj.toString());
            if (logicalExpireTime > System.currentTimeMillis()) {
                return null; // 逻辑过期时间未过期，直接返回 null 避免缓存击穿
            } else {
                redisTemplate.delete(logicalExpireKey); // 逻辑过期时间已过期，删除该键
            }
        }

        // 尝试获取分布式锁
        RLock lock = redissonClient.getLock(key);
        try {
            if (lock.tryLock()) { // 获取锁成功，再次尝试从缓存中获取数据
                result = redisTemplate.opsForValue().get(key);
                if (result == null) { // 缓存中仍然没有数据，需要从数据库中查询
                    result = getFromDB(key);
                    if (result != null) {
                        // 将查询结果写入缓存，同时设置缓存过期时间和逻辑过期时间
                        redisTemplate.opsForValue().set(key, result.toString(), 60, TimeUnit.SECONDS);
                        redisTemplate.opsForValue().set(logicalExpireKey, result.toString(), 5, TimeUnit.MINUTES);
                    } else {
                        // 数据库中也没有数据，为避免缓存穿透问题，将空值写入缓存并设置较短的过期时间
                        redisTemplate.opsForValue().set(key, null, 10, TimeUnit.SECONDS);
                    }
                }
            } else {
                if (retryCount <= 0) { // 到达重试次数限制，等待一段时间后重试
                    Thread.sleep(1000);
                    result = getData(key, retryCount - 1);
                } else {
                    throw new RuntimeException("Failed to acquire the lock for key: " + key); // 抛出异常
                }
            }
        } catch (InterruptedException | RedisException e) { // 处理 Redis 错误
            throw new RuntimeException(e);
        } finally {
            if (lock != null && lock.isHeldByCurrentThread()) { // 释放锁
                lock.unlock();
            }
        }

        return result;
    }



    @Test
    public void cacheClientTest(){
//        Course course = queryWithPassThrough("course");
//        System.out.println(course);
//        String name = queryWithPassThrough1("name", ()->null, 3600);
//        System.out.println(name);
//        String result = queryWithPassThrough1("unknown_key", () -> null, 3600);
//        System.out.println(result); // 输出 null，缓存穿透问题得到解决

        Object course = getData("jack", 3);
        System.out.println(course);
    }
}
