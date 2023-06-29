package com.student_management.demo.controller.user;

import com.student_management.demo.common.CommonResult;
import com.student_management.demo.controller.user.vo.UserBasicRespVO;
import com.student_management.demo.service.user.UserBasicService;
import com.student_management.demo.utils.token.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    @PreAuthorize("hasAuthority('/user/profile/get')")
    public CommonResult<UserBasicRespVO> getUserProfile(@RequestHeader("Authorization") String authHeader) {
        System.out.println("=================getUserProfile=================");
        String username = jwtTokenUtil.getUsernameFromToken(authHeader);//id,且学生和老师id不会重复
        System.out.println(username);
        return CommonResult.success(userBasicService.getBasicInfo(username));
    }

    @PostMapping("/profile/change-password")
    @PreAuthorize("hasAuthority('/user/profile/change-password')")
    public CommonResult<?> changePassword(@RequestParam("newPassword") String newPassword,
                                 Authentication authentication) {
        String username = authentication.getName();
        return userBasicService.changePasswd(username,newPassword);
    }

    @PostMapping("/profile/reset-password")
    @PreAuthorize("hasAuthority('/user/profile/reset-password')")
    public CommonResult<?> resetPassword(@RequestParam("newPassword") String num,
                                          Authentication authentication) {
        return userBasicService.resetPasswd(num);
    }

}
