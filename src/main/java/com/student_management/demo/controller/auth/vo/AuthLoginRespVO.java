package com.student_management.demo.controller.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthLoginRespVO {

    @Schema(description = "用户角色", required = true, example = "false")
    private boolean role;

    @Schema(description = "用户id", required = true, example = "1024")
    private Long userId;

    @Schema(description = "访问令牌", required = true, example = "happy")
    private String accessToken;

    @Schema(description = "刷新令牌", required = true, example = "nice")
    private String refreshToken;

    //@Schema(description = "过期时间", required = true)
    //private LocalDateTime expiresTime;

    private boolean isFailure = false;//是否发生错误
    private String errorInfo;//错误信息

}
