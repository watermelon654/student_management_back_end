package com.student_management.demo.common.error;

public interface ErrorCodeConstants {
    ErrorCode AUTH_LOGIN_BAD_CREDENTIALS = new ErrorCode(100500, "登录失败，账号密码不正确");
    ErrorCode AUTH_LOGIN_USER_NOT_EXIST = new ErrorCode(100501, "用户不存在，请联系学工老师！");
    ErrorCode USER_IMPORT_LIST_IS_EMPTY = new ErrorCode(1002003004, "导入学生数据不能为空！");

    ErrorCode TOKEN_EXPIRED = new ErrorCode(1002003005, "您的登录已过期，请重新登录！");

    ErrorCode ERROR_TOKEN_DATA = new ErrorCode(1002003006, "解析JWT失败，此JWT数据错误，无法解析!");
    ErrorCode ERROR_TOKEN_SIGNATURE = new ErrorCode(1002003007, "解析JWT失败，此JWT签名错误!");
    ErrorCode ERROR_TOKEN = new ErrorCode(1002003008, "解析JWT失败!");

    ErrorCode AUTHENTICATION_FAILED = new ErrorCode(1002003010,"用户登录失败，请检查后端报错!");

}
