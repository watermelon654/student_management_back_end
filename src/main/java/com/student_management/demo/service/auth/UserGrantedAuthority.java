package com.student_management.demo.service.auth;

import com.alibaba.fastjson2.JSON;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserGrantedAuthority implements GrantedAuthority {
    private String auth;

    public UserGrantedAuthority(String auth) {
        this.auth = auth;
    }

    public static List<UserGrantedAuthority> convert(String str){
        List<UserGrantedAuthority> list = new ArrayList<>();
        String[] raw = str.replace("}","")
                .replace("[","")
                .replace("]","")
                .replace("{","")
                .split(", ");
        for (String s: raw) {
            list.add(new UserGrantedAuthority(s));
        }
        return list;
    }
    @Override
    public String getAuthority() {
        return JSON.toJSONString(auth);
    }
}

