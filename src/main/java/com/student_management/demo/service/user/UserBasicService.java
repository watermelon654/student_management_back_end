package com.student_management.demo.service.user;

import com.student_management.demo.controller.user.vo.UserBasicRespVO;

import java.util.HashMap;

public interface UserBasicService {
    UserBasicRespVO getBasicInfo(String id);
    HashMap<String,String> getCurrentUserInfo(String token);
}
