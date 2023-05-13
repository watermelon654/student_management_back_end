package com.student_management.demo.mapper.dataobject.user;

import lombok.Data;

@Data
public class UserDo {
    /**
     * id
     */
    private Long id;

    /**
     * 职工号/学号
     */
    private String num;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     *
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 加密后的密码
     */
    private String passwd;
}
