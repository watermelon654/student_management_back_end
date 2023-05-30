package com.student_management.demo.common.error;

public interface ErrorCodeConstants {
    ErrorCode AUTH_LOGIN_BAD_CREDENTIALS = new ErrorCode(100500, "登录失败，账号密码不正确");
    ErrorCode AUTH_LOGIN_USER_NOT_EXIST = new ErrorCode(100501, "用户不存在，请联系学工老师！");

}
