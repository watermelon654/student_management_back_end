package com.student_management.demo.controller.summary.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false)
@ApiModel(description = "成绩表导入请求")
public class SummaryImportReqVO {
    @ApiModelProperty(value = "学生ID", required = true, example = "1")
    private Long stu_id;
    @ApiModelProperty(value = "学生学号", required = true, example = "2200022123")
    private String stu_num;
    @ApiModelProperty(value = "学生姓名", required = true, example = "张三")
    private String stu_name;
    @ApiModelProperty(value = "学生GPA", required = true, example = "3.0")
    private Double gpa;

    @ApiModelProperty(value = "学生志愿", required = true, example = "40")
    private Double vol;

    @ApiModelProperty(value = "学生科研", required = true, example = "9")
    private Integer sci;

    @ApiModelProperty(value = "学生社会实践", required = true, example = "3")
    private Integer pra;

    @ApiModelProperty(value = "学生骨干", required = true, example = "4")
    private Integer ser;

    @ApiModelProperty(value = "学生个人学年总结", required = true, example = "7")
    private Integer per;
}
