package com.student_management.demo.controller.summary.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 学生评分 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SummaryRespVO extends SummaryBaseVO {

    @Schema(description = "主键ID", example = "12017")
    private Long id;

}