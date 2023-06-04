package com.student_management.demo.mapper.dataobject.staff;

import com.baomidou.mybatisplus.annotation.TableName;
import com.student_management.demo.mapper.dataobject.user.UserDo;
import com.student_management.demo.utils.enu.SexEnum;
import lombok.Data;

@Data
@TableName(value="staff_info")//数据库表名
public class StaffDO extends UserDo {

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
     * 创建者id
     */

    private Long createUserId;

    /**
     * 更新者id
     */

    private Long updateUserId;


}
