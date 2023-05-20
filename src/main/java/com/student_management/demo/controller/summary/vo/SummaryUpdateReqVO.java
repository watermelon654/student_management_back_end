package com.student_management.demo.controller.summary.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 学生评分更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SummaryUpdateReqVO extends SummaryBaseVO {

    @Schema(description = "主键ID", example = "12017")
    @NotNull(message = "主键ID不能为空")
    private Long id;

}