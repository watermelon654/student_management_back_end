package com.student_management.demo.controller.user;

import com.student_management.demo.CommonResult;
import com.student_management.demo.controller.user.vo.UserBasicRespVO;
import com.student_management.demo.service.user.StaffBasicService;
import com.student_management.demo.service.user.StudentBasicService;
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
    private StudentBasicService studentService;
    @Resource
    private StaffBasicService staffService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @GetMapping("/profile/get")
    public CommonResult<UserBasicRespVO> getUserProfile(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        String tokenStr = jwtTokenUtil.getUsernameFromToken(token);
        String num = tokenStr.substring(0,tokenStr.length()-1);
        String role = tokenStr.substring(tokenStr.length()-1);

        if (role.equals("t")){
            //教职工
            return CommonResult.success(staffService.getBasicInfo(num, true)) ;
        } else if (role.equals("f")){
            //学生
            return CommonResult.success(studentService.getBasicInfo(num, false)) ;
        } else {
            return CommonResult.error(501,"角色参数有误！");
        }
    }

}
