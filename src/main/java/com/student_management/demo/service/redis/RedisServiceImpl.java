package com.student_management.demo.service.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service("RedisService")
@Validated
@Slf4j
public class RedisServiceImpl implements RedisService {
    @Autowired
    private  RedisTemplate<String, String> redisTemplate;


    public void setValue(String key,String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setValueWithExpiration(String key, String value, long expirationSeconds) {
        redisTemplate.opsForValue().set(key, value, expirationSeconds, TimeUnit.SECONDS);
    }

    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 集合添加
     *
     * @param key
     * @param value
     */
    public void addList(String key, String value) {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * 集合获取
     *
     * @param key
     * @return
     */
    public Set<String> getList(String key) {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        return set.members(key);
    }
}
