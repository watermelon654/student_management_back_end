package com.student_management.demo.controller.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginReqVO {

    @Schema(description = "账号", required = true, example = "2200022001")
    @NotEmpty(message = "登录账号不能为空")
    @Length(min = 4, max = 16, message = "账号长度为4-16位学号或教职工号")
    @Pattern(regexp = "^[0-9]+$", message = "账号格式为数字")
    private String username;

    @Schema(description = "密码", required = true, example = "123456")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 18, message = "密码长度在6-18个字符")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "只能包含数字、大小写字母")
    private String password;

}