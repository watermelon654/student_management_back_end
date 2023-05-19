package com.student_management.demo.controller.science.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 科研情况 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ScienceRespVO extends BaseVO {

    @Schema(description = "主键ID", example = "404")
    private Long id;
}
