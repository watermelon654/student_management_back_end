package com.student_management.demo.controller.volunteer.vo;

import lombok.Data;

@Data
public class VolunteerScoreReqVO {
    /**
     * 学号
     */
    private String stuNum;

    /**
     * 打分
     */
    private Integer score;
}
