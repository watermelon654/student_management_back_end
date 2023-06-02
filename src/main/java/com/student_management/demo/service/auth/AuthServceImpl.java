package com.student_management.demo.service.auth;


import com.student_management.demo.controller.auth.vo.AuthLoginReqVO;
import com.student_management.demo.controller.auth.vo.AuthLoginRespVO;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.dataobject.staff.StaffDO;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import com.student_management.demo.mapper.mysql.staff.StaffMapper;
import com.student_management.demo.utils.token.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static com.student_management.demo.common.error.ErrorCodeConstants.*;
import static com.student_management.demo.utils.exception.ServiceExceptionUtil.exception;

@Service("adminAuthServce")
@Slf4j
public class AuthServceImpl implements AuthService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;


    /**
     * 用户登录验证
     * @param reqVO
     * @return
     */
    @Override
    public AuthLoginRespVO login(AuthLoginReqVO reqVO) {
        // 准备被认证数据
        Authentication authentication
                = new UsernamePasswordAuthenticationToken(
                reqVO.getUsername(), reqVO.getPassword());
        // 调用AuthenticationManager验证用户名与密码
        // 执行认证，如果此过程没有抛出异常，则表示认证通过，如果认证信息有误，将抛出异常
        Authentication authenticate = authenticationManager.authenticate(authentication);

        // 如果程序可以执行到此处，则表示登录成功
        // 生成此用户数据的JWT
        //JWT的组成部分:Header(头),Payload(载荷),Signature(签名)
        System.out.println("================generateToken================");
        String token = jwtTokenUtil.generateToken(authenticate,reqVO.getUsername());

        // 返回JWT数据
        return new AuthLoginRespVO(token);
    }


}
