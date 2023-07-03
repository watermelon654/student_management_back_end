package com.student_management.demo.controller.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class AuthLoginRespVO {
    @Schema(description = "访问令牌", required = true, example = "happy")
    private String token;

}
