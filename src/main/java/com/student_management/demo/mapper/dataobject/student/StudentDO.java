package com.student_management.demo.mapper.dataobject.student;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.student_management.demo.mapper.dataobject.user.UserDo;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * 评价状态
     *
     * */
    private Integer status;

}

