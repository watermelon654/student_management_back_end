package com.student_management.demo.controller.personal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@Schema(description = "个人学年总结 - 个人学年总结导入 Response VO")
public class PersonalImportRespVO {
    @Schema(description = "创建成功的个人学年总结数组", required = true)
    private List<String> createPersonalnames;

    @Schema(description = "更新成功的个人学年总结数组", required = true)
    private List<String> updatePersonalnames;

    @Schema(description = "导入失败的个人学年总结集合,key 为用户名，value 为失败原因", required = true)
    private Map<String, String> failurePersonalnames;

    @Builder.Default
    @Schema(description = "个人学年总结数组是否为空", required = true)
    private boolean isEmpty = false;
}
