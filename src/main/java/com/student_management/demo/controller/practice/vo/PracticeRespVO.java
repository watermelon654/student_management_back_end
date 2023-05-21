package com.student_management.demo.controller.practice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 社会实践情况 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PracticeRespVO extends BaseVO {

    @Schema(description = "主键ID", example = "404")
    private Long id;
}
