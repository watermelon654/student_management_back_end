package com.student_management.demo.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("PasswordEncryption")
public class PasswordEncryptionService {

    @Resource
    private BCryptPasswordEncoder passwordEncoder;


    /**
     * 加密
     * @param password
     * @return
     */
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}

