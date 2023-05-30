package com.student_management.demo.service.redis;

import com.student_management.demo.mapper.dataobject.user.UserBasicDO;

public interface RedisService {

    void setValue(String key, String value);
    void setValueWithExpiration(String key, String value, long expirationSeconds);
    String getValue(String key);
    void deleteValue(String key);
}
