package com.student_management.demo.controller.practice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BaseVO {

    @Schema(description = "学生ID",example = "16153")
    private Long stuId;

    @Schema(description = "学生学号")
    private String stuNum;

    @Schema(description = "学生姓名",example = "芋艿")
    private String stuName;

    @Schema(description = "社会实践名称")
    private String title;

    @Schema(description = "项目负责人")
    private String director;

    @Schema(description = "组织机构")
    private String constitution;

    @Schema(description = "社会实践内容")
    private String content;

    @Schema(description = "参与时长")
    private String time;

    @Schema(description = "项目结果")
    private String result;

    @Schema(description = "评分")
    private Integer score;

    @Schema(description = "状态", example = "1")
    private Integer status;

}
