package com.student_management.demo.controller.grade.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;


/**
 * GPA 导入 responseVO
 */
@Schema(description = "绩点 - 绩点导入 Response VO")
@Data
@Builder
public class GradeImportRespVO {
    @Schema(description = "创建成功的绩点数组", required = true)
    private List<String> createGradenames;

    @Schema(description = "更新成功的绩点数组", required = true)
    private List<String> updateGradenames;

    @Schema(description = "导入失败的绩点集合,key 为用户名，value 为失败原因", required = true)
    private Map<String, String> failureGradenames;

    @Builder.Default
    @Schema(description = "绩点数组是否为空", required = true)
    private boolean isEmpty = false;

}
