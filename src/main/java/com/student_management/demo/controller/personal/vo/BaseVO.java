package com.student_management.demo.controller.personal.vo;

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

    @Schema(description = "学习情况总结",example = "很好")
    private String school;

    @Schema(description = "社会实践总结",example = "很好")
    private String society;

    @Schema(description = "自我评价",example = "很好")
    private String self;


}
