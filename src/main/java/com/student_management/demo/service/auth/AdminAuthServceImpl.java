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
        AuthLoginRespVO respVO = new AuthLoginRespVO();
        if (reqVO.isRole()) {
            StaffDO stuff = staffMapper.selectStaffByNum(reqVO.getUsername());
            if (stuff == null) {//用户不存在
                respVO.setFailure(true);
                respVO.setErrorInfo("用户不存在，请联系学工老师！");
                return respVO;
            }
            if (!reqVO.getPassword().equals(stuff.getPasswd())) {
                respVO.setFailure(true);
                respVO.setErrorInfo("登录失败，账号密码不正确！");
                return respVO;
            }
            // 根据学号/职工号+角色生成访问令牌和刷新令牌
            String tokenStr = reqVO.getUsername()+ "t";
            //String username = reqVO.getUsername();
            respVO.setRole(reqVO.isRole());
            respVO.setUserId(stuff.getId());
            respVO.setAccessToken(jwtTokenUtil.generateAccessToken(tokenStr));
            respVO.setRefreshToken(jwtTokenUtil.generateRefreshToken(tokenStr));
            return respVO;
        } else {
            StudentDO studentDO = studentMapper.selectStudentByNum(reqVO.getUsername());
            if (studentDO == null) {//用户不存在
                respVO.setFailure(true);
                respVO.setErrorInfo("用户不存在，请联系学工老师！");
                return respVO;
            }
            if (!reqVO.getPassword().equals(studentDO.getPasswd())) {
                respVO.setFailure(true);
                respVO.setErrorInfo("登录失败，账号密码不正确！");
                return respVO;
            }
            //String username = reqVO.getUsername();
            // 根据学号和角色生成访问令牌和刷新令牌
            String tokenStr = reqVO.getUsername()+ "f";
            respVO.setRole(reqVO.isRole());
            respVO.setUserId(studentDO.getId());
            respVO.setAccessToken(jwtTokenUtil.generateAccessToken(tokenStr));
            respVO.setRefreshToken(jwtTokenUtil.generateRefreshToken(tokenStr));
            return respVO;
        }
    }


}
