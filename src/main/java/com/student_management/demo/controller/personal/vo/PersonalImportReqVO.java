package com.student_management.demo.controller.personal.vo;

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
@ApiModel(description = "个人学年总结表导入请求")
public class PersonalImportReqVO {
    @ApiModelProperty(value = "学生ID", required = true, example = "1")
    private Long stuId;
    @ApiModelProperty(value = "学生学号", required = true, example = "2200022123")
    private String stuNum;
    @ApiModelProperty(value = "学生姓名", required = true, example = "张三")
    private String stuName;
    @ApiModelProperty(value = "学习情况总结", required = true, example = "我的学习情况良好")
    private String school;
    @ApiModelProperty(value = "社会实践总结", required = true, example = "我的社会实践总结情况良好")
    private String society;
    @ApiModelProperty(value = "自我评价", required = true, example = "我认真学习，积极参与")
    private String self;
    @ApiModelProperty(value = "分数", required = true, example = "8")
    private Integer score;
    @ApiModelProperty(value = "状态", required = true, example = "1")
    private Integer status;
}
