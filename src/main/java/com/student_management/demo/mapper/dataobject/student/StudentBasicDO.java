package com.student_management.demo.mapper.dataobject.student;

import com.baomidou.mybatisplus.annotation.TableName;
import com.student_management.demo.utils.enu.SexEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
public class StudentBasicDO {
    /**
     * id
     */
    private Long id;

    /**
     * 学号
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
     * 学年名
     */

    private String yearName;

    /**
     * 专业名
     */

    private String majorName;

    /**
     * 学苑名
     */

    private String className;

    /**
     * 更新时间
     */

    private Timestamp updateTime;

}
