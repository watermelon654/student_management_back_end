package com.student_management.demo.controller.service.vo;

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
@ApiModel(description = "骨干服务表导入请求")
public class ServiceImportReqVO {
    @ApiModelProperty(value = "学生ID", required = true, example = "1")
    private Long stu_id;
    @ApiModelProperty(value = "学生学号", required = true, example = "2200022123")
    private String stu_num;
    @ApiModelProperty(value = "学生姓名", required = true, example = "张三")
    private String stu_name;
    @ApiModelProperty(value = "岗位名称", required = true, example = "智能餐厅管理系统")
    private String title;
    @ApiModelProperty(value = "所在岗位负责人", required = true, example = "李四")
    private String director;
    @ApiModelProperty(value = "所在岗位负责单位", required = true, example = "北京大学")
    private String constitution;
    @ApiModelProperty(value = "岗位职责", required = true, example = "hahahhaah")
    private String content;
    @ApiModelProperty(value = "服务时长", required = true, example = "10")
    private String time;
    @ApiModelProperty(value = "结果", required = true, example = "100")
    private String result;
    @ApiModelProperty(value = "分数", required = true, example = "8")
    private Integer score;
    @ApiModelProperty(value = "状态", required = true, example = "1")
    private Integer status;
}
