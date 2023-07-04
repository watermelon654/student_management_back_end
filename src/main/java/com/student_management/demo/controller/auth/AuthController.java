package com.student_management.demo.controller.auth;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.student_management.demo.controller.auth.vo.AuthLoginRespVO;
import com.student_management.demo.controller.auth.vo.AuthLoginReqVO;

import com.student_management.demo.service.auth.AuthService;
import com.student_management.demo.service.redis.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

import com.student_management.demo.common.CommonResult;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import static com.student_management.demo.common.error.ErrorCodeConstants.CAPTCHA_MISMATCH;
import static com.student_management.demo.utils.exception.ServiceExceptionUtil.exception;

@RestController
@RequestMapping("/auth")
@Validated
@Slf4j
public class AuthController {

    @Resource
    private AuthService authService;
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Resource
    RedisService redisService;
    @PostMapping("/login")
    @PermitAll
    @Operation(summary = "使用账号密码登录")
    public CommonResult<AuthLoginRespVO> login( @RequestBody AuthLoginReqVO reqVO) {
        String storedCaptcha = redisService.getValue("captcha");
        if (reqVO.getCaptchaText().equalsIgnoreCase(storedCaptcha)) {
            return CommonResult.success(authService.login(reqVO));
        } else {
            throw exception(CAPTCHA_MISMATCH);
        }

    }

    @GetMapping("/captcha")
    @PermitAll
    @Operation(summary = "获得图片验证码")
    public CommonResult<?> showCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("获取图片验证码");
        // 生成验证码文本
        String captchaText = defaultKaptcha.createText();

        // 将验证码文本存储在redis中，以供后续验证
        redisService.setValue("captcha", captchaText);
        // 生成验证码图片
        ByteArrayOutputStream out = null;
        BufferedImage captchaImage = defaultKaptcha.createImage(captchaText);

        // 将验证码图片写入响应
        out=new ByteArrayOutputStream();
        ImageIO.write(captchaImage,"jpg",out);
        // 对字节组Base64编码
        return CommonResult.success(Base64.getEncoder().encodeToString(out.toByteArray()));
    }


}

