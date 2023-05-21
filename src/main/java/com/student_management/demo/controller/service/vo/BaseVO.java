package com.student_management.demo.controller.service.vo;

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

    @Schema(description = "服务岗位名称")
    private String title;

    @Schema(description = "岗位负责人")
    private String director;

    @Schema(description = "岗位所在单位")
    private String constitution;

    @Schema(description = "岗位工作内容")
    private String content;

    @Schema(description = "在岗实践")
    private String time;

    @Schema(description = "在岗期间成就")
    private String result;

    @Schema(description = "评分")
    private Integer score;

    @Schema(description = "状态", example = "1")
    private Integer status;

}
