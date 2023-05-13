package com.student_management.demo.controller.grade.vo;

import lombok.Data;

@Data
public class GradeRespVO {

    /**
     * 学号
     */
    private Long stu_num;

    /**
     * 姓名
     */
    private String stu_name;

    /**
     * 评价状态
     *
     */
    private Integer status;
    /**
     * GPA
     */
    private float gpa;

    /**
     * 备注
     */
    //private String note;

}
