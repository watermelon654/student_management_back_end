package com.student_management.demo.controller.error;

import com.student_management.demo.common.CommonResult;
import com.student_management.demo.common.error.ErrorCode;
import com.student_management.demo.common.error.ServerException;
import io.swagger.annotations.Api;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

import static com.student_management.demo.utils.exception.ServiceExceptionUtil.exception;

@RestController
public class FilterErrorController {

    //@ExceptionHandler(Exception.class)
    @GetMapping("/error/jwt")
    @PermitAll
    public CommonResult<?> handleFilterException(HttpServletRequest request) {
        ErrorCode exception = (ErrorCode) request.getAttribute("filterError");
        // 处理异常，例如返回自定义的错误响应
        throw exception(exception);
    }
}