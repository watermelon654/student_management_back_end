package com.student_management.demo.mapper.dataobject.user;

import com.student_management.demo.utils.enu.SexEnum;
import lombok.Data;

/**
 * 本来设计的是student和stuff的父类，但是跑的时候报错了，
 * 属性没继承上，目前就是不继承，后面完善的时候再看怎么回事
 */
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
     * 用户性别
     *
     * 枚举类 {@link SexEnum}
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String passwd;
}
