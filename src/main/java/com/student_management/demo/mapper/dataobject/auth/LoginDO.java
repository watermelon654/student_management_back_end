package com.student_management.demo.mapper.dataobject.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginDO implements Serializable {

    private String username;
    private String passwd;

}
