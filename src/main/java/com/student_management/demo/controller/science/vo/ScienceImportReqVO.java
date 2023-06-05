package com.student_management.demo.controller.science.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "科研项目表导入请求")
public class ScienceImportReqVO {
    @ApiModelProperty(value = "学生ID", required = true, example = "1")
    private Long stuId;
    @ApiModelProperty(value = "学生学号", required = true, example = "2200022123")
    private String stuNum;
    @ApiModelProperty(value = "学生姓名", required = true, example = "张三")
    private String stuName;
    @ApiModelProperty(value = "项目名称", required = true, example = "智能餐厅管理系统")
    private String title;
    @ApiModelProperty(value = "负责人", required = true, example = "李四")
    private String director;
    @ApiModelProperty(value = "组织机构", required = true, example = "北京大学")
    private String constitution;
    @ApiModelProperty(value = "项目级别", required = true, example = "3")
    private Integer level;
    @ApiModelProperty(value = "时间", required = true, example = "10")
    private String time;
    @ApiModelProperty(value = "项目成果", required = true, example = "该项目获得2019年省级信息技术大赛三等奖")
    private String result;
    @ApiModelProperty(value = "创建者id", required = true, example = "1")
    private Long createUserId;
    @ApiModelProperty(value = "更新者id", required = true, example = "1")
    private Long updateUserId;

}
