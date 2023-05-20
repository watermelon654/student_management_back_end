package com.student_management.demo.controller.science.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BaseVO {

    @Schema(description = "学生ID",example = "16153")
    private Long stu_id;

    @Schema(description = "学生学号")
    private String stu_num;

    @Schema(description = "学生姓名",example = "芋艿")
    private String stu_name;

    @Schema(description = "项目名称")
    private String title;

    @Schema(description = "负责人")
    private String director;

    @Schema(description = "组织机构")
    private String constitution;

    @Schema(description = "项目级别")
    private Integer level;

    @Schema(description = "参与时间")
    private String time;

    @Schema(description = "项目成功")
    private String result;

    @Schema(description = "评分")
    private Integer score;

    @Schema(description = "状态", example = "1")
    private Integer status;

}
