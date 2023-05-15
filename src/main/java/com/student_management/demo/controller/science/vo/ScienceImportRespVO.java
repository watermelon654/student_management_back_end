package com.student_management.demo.controller.science.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@Schema(description = "科研 - 科研导入 Response VO")
public class ScienceImportRespVO {
    @Schema(description = "创建成功的科研数组", required = true)
    private List<String> createSciencenames;

    @Schema(description = "更新成功的科研数组", required = true)
    private List<String> updateSciencenames;

    @Schema(description = "导入失败的科研集合,key 为用户名，value 为失败原因", required = true)
    private Map<String, String> failureSciencenames;

    @Builder.Default
    @Schema(description = "科研数组是否为空", required = true)
    private boolean isEmpty = false;
}
