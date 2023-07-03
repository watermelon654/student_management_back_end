package com.student_management.demo.controller.auth;

import com.student_management.demo.controller.auth.vo.AuthLoginRespVO;
import com.student_management.demo.controller.auth.vo.AuthLoginReqVO;

import com.student_management.demo.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

import com.student_management.demo.common.CommonResult;

@RestController
@RequestMapping("/auth")
@Validated
@Slf4j
public class AuthController {

    @Resource
    private AuthService authService;

    @PostMapping("/login")
    @PermitAll
    @Operation(summary = "使用账号密码登录")
    public CommonResult<AuthLoginRespVO> login(@RequestBody AuthLoginReqVO reqVO) {
        return CommonResult.success(authService.login(reqVO));
    }



}

