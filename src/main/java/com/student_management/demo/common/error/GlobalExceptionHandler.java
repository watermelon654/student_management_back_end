package com.student_management.demo.common.error;

import com.student_management.demo.common.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ServerException.class)
    public CommonResult<ServerException> handleCustomException(ServerException ex) {
        //返回错误信息
        return CommonResult.error(ex.getCode(), ex.getMessage());
    }
}
