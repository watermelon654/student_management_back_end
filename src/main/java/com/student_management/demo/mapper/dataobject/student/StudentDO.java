package com.student_management.demo.mapper.dataobject.student;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.student_management.demo.mapper.dataobject.user.UserDo;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.student_management.demo.utils.enu.SexEnum;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@TableName(value="stu_info")//数据库表名
public class StudentDO extends UserDo {


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
     * 加密后的密码
     */
    private String passwd;

    /**
     * 学年 ID
     */

    private Long yearId;

    /**
     * 专业 ID
     */

    private Long majorId;

    /**
     * 班级 ID
     */

    private Long classId;

    /**
     * 创建者id
     */

    private Long createUserId;

    /**
     * 创建时间
     */

    private Timestamp createTime;

    /**
     * 更新者id
     */
    
    private Long updateUserId;

    /**
     * 更新时间
     */

    private Timestamp updateTime;

    /**
     * 是否删除
     */

    private Boolean isDel;

}

