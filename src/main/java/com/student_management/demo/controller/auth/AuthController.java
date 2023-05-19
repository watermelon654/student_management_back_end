package com.student_management.demo.controller.auth;

import cn.hutool.core.util.StrUtil;
import com.student_management.demo.controller.auth.vo.AuthLoginRespVO;
import com.student_management.demo.controller.auth.vo.AuthLoginReqVO;

import com.student_management.demo.service.auth.AdminAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.student_management.demo.CommonResult;

@RestController
@RequestMapping("/auth")
@Validated
@Slf4j
public class AuthController {

    @Resource
    private AdminAuthService authService;
    //@Resource
    //private SecurityProperties securityProperties;

    @PostMapping("/login")
    @PermitAll
    @Operation(summary = "使用账号密码登录")
    public CommonResult<AuthLoginRespVO> login(@RequestBody AuthLoginReqVO reqVO) {
        AuthLoginRespVO respVO = authService.login(reqVO);
        if (respVO.isFailure())
            return CommonResult.error(501, respVO.getErrorInfo());
        return CommonResult.success(authService.login(reqVO));
    }

    //@PostMapping("/logout")
    //@PermitAll
    //@Operation(summary = "登出系统")
    //public CommonResult<Boolean> logout(HttpServletRequest request) {
    //    String token = obtainAuthorization(request, securityProperties.getTokenHeader());
    //    if (StrUtil.isNotBlank(token)) {
    //        authService.logout(token, LoginLogTypeEnum.LOGOUT_SELF.getType());
    //    }
    //    return CommonResult.success(true);
    //}

    //@PostMapping("/refresh-token")
    //@PermitAll
    //@Operation(summary = "刷新令牌")
    //@Parameter(name = "refreshToken", description = "刷新令牌", required = true)
    //public CommonResult<AuthLoginRespVO> refreshToken(@RequestParam("refreshToken") String refreshToken) {
    //    return CommonResult.success(authService.refreshToken(refreshToken));
    //}



}

