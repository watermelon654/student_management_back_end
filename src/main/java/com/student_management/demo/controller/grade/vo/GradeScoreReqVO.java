package com.student_management.demo.controller.grade.vo;

import lombok.Data;

@Data
public class GradeScoreReqVO {
    /**
     * 学号
     */
    private String stuNum;

    /**
     * 打分
     */
    private Integer score;
}
