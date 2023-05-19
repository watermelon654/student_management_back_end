package com.student_management.demo.mapper.dataobject.staff;

import com.baomidou.mybatisplus.annotation.TableName;
import com.student_management.demo.mapper.dataobject.user.UserDo;
import lombok.Data;

@Data
@TableName(value="staff_info")//数据库表名
public class StaffDO extends UserDo {

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

    /**
     * 职位
     */
    private String job;

    /**
     * 级别
     */
    private Long level;

}
