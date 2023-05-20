package com.student_management.demo.controller.summary.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "管理后台 - 学生评分创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SummaryCreateReqVO extends SummaryBaseVO {

}