package com.student_management.demo.controller.grade.vo.Judge;

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

    /**
     * 更新者职工号
     */
    private String judgeNum;
}
