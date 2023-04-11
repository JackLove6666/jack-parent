package com.cloud.jack.wms.stock.util;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedissonLocker {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * @param key     锁的key
     * @param waitTime 尝试获取锁的最大等待时间，超过这个值，则认为获取锁失败
     * @param expireTime   锁的持有时间,超过这个时间锁会自动失效（值应设置为大于业务处理的时间，确保在锁有效期内业务能处理完）
     * @return
     */

    public Boolean tryDoBusiness(String key,int waitTime,int expireTime,RedissonBusinessCallBack redissonBusinessCallBack){
        RLock redissonClientLock = redissonClient.getLock(key);

        try {
            boolean  tryLock = redissonClientLock.tryLock(waitTime, expireTime, TimeUnit.SECONDS);
            if(tryLock){
                redissonBusinessCallBack.call();
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("获取锁失败");
        }finally {
            redissonClientLock.unlock();
        }
        return false;
    }
}
