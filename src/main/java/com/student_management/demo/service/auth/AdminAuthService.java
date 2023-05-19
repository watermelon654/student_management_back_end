package com.student_management.demo.service.auth;

import com.student_management.demo.controller.auth.vo.AuthLoginReqVO;
import com.student_management.demo.controller.auth.vo.AuthLoginRespVO;

public interface AdminAuthService {

    /**
     * 登录验证
     * @param reqVO
     * @return
     */
    AuthLoginRespVO login(AuthLoginReqVO reqVO);
}
