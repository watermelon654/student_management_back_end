package com.student_management.demo.utils.exception;

import com.student_management.demo.common.error.ServerException;
import com.student_management.demo.common.error.ErrorCode;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ServiceExceptionUtil {
    private static final ConcurrentMap<Integer, String> MESSAGES = new ConcurrentHashMap<>();

    public static ServerException exception(ErrorCode errorCode) {
        String messagePattern = MESSAGES.getOrDefault(errorCode.getCode(), errorCode.getMsg());
        return exception0(errorCode.getCode(), messagePattern);
    }

    public static ServerException exception0(Integer code, String messagePattern) {
        //String message = doFormat(code, messagePattern, params);
        return new ServerException(code, messagePattern);
    }
}
