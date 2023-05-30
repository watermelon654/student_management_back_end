package com.student_management.demo.controller.summary.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SummaryBaseVO {
    @Schema(description = "学生ID", example = "1")
    @NotNull(message = "学生ID不能为空")
    private Long stuId;

    @Schema(description = "学生学号")
    @NotNull(message = "学生学号不能为空")
    private String stuNum;

    @Schema(description = "学生姓名", example = "李四")
    @NotNull(message = "学生姓名不能为空")
    private String stuName;

    @Schema(description = "学生GPA")
    private Integer gpa;

    @Schema(description = "学生志愿")
    private Integer vol;

    @Schema(description = "学生科研")
    private Integer sci;

    @Schema(description = "学生社会实践")
    private Integer pra;

    @Schema(description = "学生骨干")
    private Integer ser;

    @Schema(description = "学生个人学年总结")
    private Integer per;
    @Schema(description = "状态")
    private boolean status;
}
