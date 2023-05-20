package com.student_management.demo.controller.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@Schema(description = "骨干服务 - 骨干服务导入 Response VO")
public class ServiceImportRespVO {
    @Schema(description = "创建成功的骨干服务数组", required = true)
    private List<String> createServicenames;

    @Schema(description = "更新成功的骨干服务数组", required = true)
    private List<String> updateServicenames;

    @Schema(description = "导入失败的骨干服务集合,key 为用户名，value 为失败原因", required = true)
    private Map<String, String> failureServicenames;

    @Builder.Default
    @Schema(description = "骨干服务数组是否为空", required = true)
    private boolean isEmpty = false;
}
