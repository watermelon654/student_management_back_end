package com.student_management.demo.controller.grade.vo;

import lombok.Data;

@Data
public class GradeRespVO {

    /**
     * 学号
     */
    private Long stuNum;

    /**
     * 姓名
     */
    private String stuName;

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
