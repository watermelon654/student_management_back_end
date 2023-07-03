package com.student_management.demo.controller.volunteer.vo.Judge;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;


/**
 * 服务时长 导入 responseVO
 */
@Schema(description = "服务时长 - 服务时长导入 Response VO")
@Data
@Builder
public class VolunteerImportRespVO {
    @Schema(description = "创建成功的服务时长数组", required = true)
    private List<String> createVolunteernames;

    @Schema(description = "更新成功的服务时长数组", required = true)
    private List<String> updateVolunteernames;

    @Schema(description = "导入失败的服务时长集合,key 为用户名，value 为失败原因", required = true)
    private Map<String, String> failureVolunteernames;

    @Builder.Default
    @Schema(description = "服务时长数组是否为空", required = true)
    private boolean isEmpty = false;

}
