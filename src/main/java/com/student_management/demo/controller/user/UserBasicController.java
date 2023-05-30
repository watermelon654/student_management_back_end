package com.student_management.demo.controller.user;

import com.student_management.demo.common.CommonResult;
import com.student_management.demo.controller.user.vo.UserBasicRespVO;
import com.student_management.demo.service.user.UserBasicService;
import com.student_management.demo.utils.token.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserBasicController {

    @Resource
    private UserBasicService userBasicService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/profile/get")
    public CommonResult<UserBasicRespVO> getUserProfile(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);//学号/职工号
        Long id = Long.parseLong(username);
        return CommonResult.success(userBasicService.getBasicInfo(id));
    }

}
