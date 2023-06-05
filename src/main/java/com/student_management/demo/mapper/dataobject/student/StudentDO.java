package com.student_management.demo.mapper.dataobject.student;

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


}

