package com.student_management.demo.controller.practice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@Schema(description = "社会实践 - 社会实践导入 Response VO")
public class PracticeImportRespVO {
    @Schema(description = "创建成功的社会实践数组", required = true)
    private List<String> createPracticenames;

    @Schema(description = "更新成功的社会实践数组", required = true)
    private List<String> updatePracticenames;

    @Schema(description = "导入失败的社会实践集合,key 为用户名，value 为失败原因", required = true)
    private Map<String, String> failurePracticenames;

    @Builder.Default
    @Schema(description = "社会实践数组是否为空", required = true)
    private boolean isEmpty = false;
}
