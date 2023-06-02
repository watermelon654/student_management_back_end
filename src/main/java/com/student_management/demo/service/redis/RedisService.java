package com.student_management.demo.service.redis;

public interface RedisService {

    void setValue(String key, String value);
    void setValueWithExpiration(String key, String value, long expirationSeconds);
    String getValue(String key);
    void deleteValue(String key);
}
