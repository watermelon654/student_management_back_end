package com.student_management.demo.mapper.dataobject.grade;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value="grade")//数据库表名
public class GradeDO {
    /**
     * id
     *
     */
    @TableId(type = IdType.AUTO)//设为自增
    private Long id;

    /**
     * 学生id
     */
    private Long stuId;

    /**
     * 学号
     */
    private String stuNum;

    /**
     * 姓名
     */
    private String stuName;

    /**
     * GPA
     */
    private float gpa;

//    private int createUserId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}
