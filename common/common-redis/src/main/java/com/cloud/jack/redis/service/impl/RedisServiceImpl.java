package com.cloud.jack.redis.service.impl;

import com.cloud.jack.redis.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class RedisServiceImpl implements IRedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void set(String key, String value) {

    }

    @Override
    public void set(String key, String value, Long seconds) {

    }

    @Override
    public boolean setnx(String key, String value) {
        return false;
    }

    @Override
    public boolean setnx(String key, String value, Long seconds) {
        return false;
    }

    @Override
    public void setnx(String key, String value, long timeout, TimeUnit unit) {

    }

    @Override
    public boolean setLock(String key, Long seconds) {
        return false;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public Long getLong(String key) {
        return null;
    }

    @Override
    public boolean exists(String key) {
        return false;
    }

    @Override
    public List<String> get(List<String> keys) {
        return null;
    }

    @Override
    public boolean expire(String key, long expire) {
        return false;
    }

    @Override
    public Long getExpire(String key) {
        return null;
    }

    @Override
    public void remove(String key) {

    }

    @Override
    public void removeByRegexKey(String key) {

    }

    @Override
    public Long increment(String key, long delta) {
        return null;
    }

    @Override
    public Long increment(String key, long delta, long expire) {
        return null;
    }

    @Override
    public Long decrement(String key, long delta) {
        return null;
    }

    @Override
    public Long decrement(String key, long delta, long expire) {
        return null;
    }

    @Override
    public <T> List<T> getList(String key, Class<T> clazz, Supplier<List<T>> supplier) {
        return null;
    }

    @Override
    public <T> List<T> getList(String key, Class<T> clazz, Supplier<List<T>> supplier, long timeout) {
        return null;
    }

    @Override
    public <T> List<T> getList(String key, Class<T> clazz, Supplier<List<T>> supplier, long timeout, int number) {
        return null;
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz, Supplier<T> supplier) {
        return null;
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz, Supplier<T> supplier, long timeout) {
        return null;
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz, Supplier<T> supplier, long timeout, int number) {
        return null;
    }

    @Override
    public Long setAdd(String key, String... values) {
        return null;
    }

    @Override
    public Boolean setAdd(String key, String value, double score) {
        return null;
    }

    @Override
    public Long setSize(String key) {
        return null;
    }

    @Override
    public void hashPutAll(String key, Map<String, String> maps) {

    }

    @Override
    public void hashPut(String key, String hashKey, String value) {

    }

    @Override
    public boolean hashExists(String key, String field) {
        return false;
    }
}
