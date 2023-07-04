package com.student_management.demo.controller.grade.vo.Judge;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class JudgeGradeRespVO {
    @Schema(description = "学生ID", example = "13470")
    @NotNull(message = "学生ID不能为空")
    private Long stuId;

    @Schema(description = "学生学号")
    @NotNull(message = "学生学号不能为空")
    private String stuNum;

    @Schema(description = "学生姓名", example = "李四")
    @NotNull(message = "学生姓名不能为空")
    private String stuName;

    @Schema(description = "学生GPA")
    private float gpa;

    @Schema(description = "评分")
    private Integer score;

    @Schema(description = "创建时间")
    private Timestamp createTime;

    @Schema(description = "更新时间")
    private Timestamp updateTime;

    @Schema(description = "是否删除")
    private int isDel;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
