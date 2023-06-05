package com.student_management.demo.service.redis;

import java.util.Set;

public interface RedisService {

    void setValue(String key, String value);
    void setValueWithExpiration(String key, String value, long expirationSeconds);
    String getValue(String key);
    void deleteValue(String key);
    void addList(String key, String value);
    Set<String> getList(String key);

}
