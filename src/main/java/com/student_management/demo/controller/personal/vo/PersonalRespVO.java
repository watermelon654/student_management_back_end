package com.student_management.demo.controller.personal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 个人学年总结 Response VO")
@Data
@ToString(callSuper = true)
public class PersonalRespVO extends BaseVO{
    @Schema(description = "主键ID", example = "404")
    private Long id;
}
