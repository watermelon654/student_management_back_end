package com.student_management.demo.controller.summary.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@Schema(description = "成绩 - 成绩导入 Response VO")
public class SummaryImportRespVO {
    @Schema(description = "创建成功的成绩数组", required = true)
    private List<String> createSummarynames;

    @Schema(description = "更新成功的成绩数组", required = true)
    private List<String> updateSummarynames;

    @Schema(description = "导入失败的成绩集合,key 为用户名，value 为失败原因", required = true)
    private Map<String, String> failureSummarynames;

    @Builder.Default
    @Schema(description = "成绩数组是否为空", required = true)
    private boolean isEmpty = false;

}
