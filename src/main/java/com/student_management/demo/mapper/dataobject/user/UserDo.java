package com.student_management.demo.mapper.dataobject.user;

import com.student_management.demo.utils.enu.SexEnum;
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
     * 密码
     */
    private String passwd;

    private boolean isDel;
}

