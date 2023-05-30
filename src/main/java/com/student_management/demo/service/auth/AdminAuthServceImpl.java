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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.student_management.demo.common.error.ErrorCodeConstants.*;
import static com.student_management.demo.utils.exception.ServiceExceptionUtil.exception;

@Service("adminAuthServce")
@Slf4j
public class AdminAuthServceImpl implements AdminAuthService{

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private StaffMapper staffMapper;
    @Resource
    private StudentMapper studentMapper;

    /**
     * 用户登录验证
     * @param reqVO
     * @return
     */
    @Override
    public AuthLoginRespVO login(AuthLoginReqVO reqVO) {
       // 获取id
        Long id = reqVO.isRole() ?
               checkStaffPasswd(reqVO.getUsername(), reqVO.getPassword()) :
               checkStudentPasswd(reqVO.getUsername(), reqVO.getPassword());

        // 根据id生成访问令牌和刷新令牌
        String tokenStr = String.valueOf(id);
        return new AuthLoginRespVO(
                reqVO.isRole(),
                id,
                jwtTokenUtil.generateAccessToken(tokenStr),
                jwtTokenUtil.generateRefreshToken(tokenStr));
    }

    /**
     * 学生登录密码验证
     * @param username
     * @param passwd
     * @return
     */
    private Long checkStudentPasswd(String username, String passwd) {
        StudentDO studentDO = studentMapper.selectStudentByNum(username);
        if (studentDO == null) {
            //用户不存在
            throw exception(AUTH_LOGIN_USER_NOT_EXIST);
        }
        if (!passwd.equals(studentDO.getPasswd())) {
            //密码错误
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        return studentDO.getId();
    }

    /**
     * 职工登录密码验证
     * @param username
     * @param passwd
     * @return
     */
    private Long checkStaffPasswd(String username, String passwd) {
        StaffDO staffDO = staffMapper.selectStaffByNum(username);
        if (staffDO == null) {
            //用户不存在
            throw exception(AUTH_LOGIN_USER_NOT_EXIST);
        }
        if (!passwd.equals(staffDO.getPasswd())) {
            //密码错误
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        return staffDO.getId();
    }



}
