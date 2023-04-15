package com.cloud.jack.app.test.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存更新时，是先清缓存还是先更新数据库？
 * • 先清缓存：可能造成刚清理缓存还没有更新数据库，高并发下，其他线程直接查询了数据库过期数据到缓存中，这种情况非常严重，直接导致后续所有的请求缓存和数据库不一致。
 * • 先更新据库：可能造成刚更新数据库，还没清空缓存就有线程从缓存拿到了旧数据，这种情况概率比较小，影响范围有限，只对这一次的查询结果有问题。
 * 显而易见，通常情况下，先更新数据库，然后清空缓存。
 */
public class GenericCachedDaoTest {


    //缓存对象，这里用JVM缓存
    private static Map<String, Object> cache = new HashMap<>();
    //读写锁
    private static ReentrantReadWriteLock rw = new ReentrantReadWriteLock();

    /**
     * 读缓存
     */
    public static Object get(String key) {
        rw.readLock().lock();
        try {
            Object value = cache.get(key);
            if (value != null) {
                return value;
            }
        } finally {
            rw.readLock().unlock();
        }
        //缓存中没有，从数据库中读取
        rw.writeLock().lock();
        try {
            //再次判断缓存中是否有数据，因为可能在获取写锁的过程中，其他线程已经写入了数据
            Object value = cache.get(key);
            if (value == null) {
                //从数据库中读取数据
                value = "aaa";
                //写入缓存
                cache.put(key, value);
            }
            return value;
        } finally {
            rw.writeLock().unlock();
        }
    }

    //更新数据
    public static void update(String key, Object value) {
        //加写锁
        rw.writeLock().lock();
        try {
            //更新数据库
            //updateDB(key, value);
            //清空缓存
            cache.remove(key);
        } finally {
            rw.writeLock().unlock();
        }
    }
}
