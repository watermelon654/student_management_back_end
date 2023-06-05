package com.student_management.demo;

import com.student_management.demo.mapper.dataobject.user.UserPermissionDO;
import com.student_management.demo.mapper.mysql.user.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTests {

    @Autowired
    UserMapper mapper;

    @Test
    void testGetLoginInfoByUsernameSuccessfully() {
        // 测试数据
        String username = "2200022001";
        // 断言不会抛出异常
        Assertions.assertDoesNotThrow(() -> {
            // 执行查询
            UserPermissionDO admin = mapper.selectUserInfoByUserNum(username);
            System.out.println("result >>> " + admin.getUrls());
            // 断言查询结果不为null
            Assertions.assertNotNull(admin);
        });
    }

    @Test
    void testGetLoginInfoByUsernameFailBecauseNotFound() {
        // 测试数据
        String username = "00001";
        // 断言不会抛出异常
        Assertions.assertDoesNotThrow(() -> {
            // 执行查询
            UserPermissionDO admin = mapper.selectUserInfoByUserNum(username);
            System.out.println("result >>> " + admin);
            // 断言查询结果为null
            Assertions.assertNull(admin);
        });
    }

}

