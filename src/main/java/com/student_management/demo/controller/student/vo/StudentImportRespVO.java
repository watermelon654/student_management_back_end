package com.student_management.demo.controller.student.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "学生信息 - 信息导入 Response VO")
@Data
@Builder
public class StudentImportRespVO {
    @Schema(description = "创建成功的学生数组", required = true)
    private List<String> createsStudentnames;

    @Schema(description = "更新成功的学生数组", required = true)
    private List<String> updateStudentnames;

    @Schema(description = "导入失败的学生集合,key 为用户名，value 为失败原因", required = true)
    private Map<String, String> failureStudentnames;

    @Builder.Default
    @Schema(description = "学生数组是否为空", required = true)
    private boolean isEmpty = false;
}
