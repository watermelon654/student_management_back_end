package com.student_management.demo.service.user;

import com.student_management.demo.common.CommonResult;
import com.student_management.demo.controller.user.vo.UserBasicRespVO;

import java.util.HashMap;

public interface UserBasicService {
    UserBasicRespVO getBasicInfo(String id);
    HashMap<String,String> getCurrentUserInfo(String token);

    CommonResult<?> changePasswd(String username, String newPassword);

    CommonResult<?> resetPasswd(String num);
}
