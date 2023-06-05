package com.student_management.demo.mapper.dataobject.grade;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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

//    /**
//     * 评价状态
//     * 0：未确认(default)
//     * 1：已确认
//     * 2：有误
//     */
//    private Integer status=0;

    /**
     * GPA
     */
    private float gpa;

//    /**
//     * 备注
//     */
//    private String note;

/*    *//**
     * 打分
     *//*
    private Integer score;*/

}
