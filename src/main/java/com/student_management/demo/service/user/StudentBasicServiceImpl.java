package com.student_management.demo.service.user;

import com.student_management.demo.controller.user.vo.UserBasicRespVO;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("StudentBasicServce")
@Slf4j
public class StudentBasicServiceImpl implements StudentBasicService{
    @Resource
    private StudentMapper studentMapper;

    public UserBasicRespVO getBasicInfo(String num, boolean role) {
        StudentDO studentDO = studentMapper.selectStudentByNum(num);
        UserBasicRespVO respVO = new UserBasicRespVO();
        respVO.setUsername(studentDO.getName());
        respVO.setNum(studentDO.getNum());
        respVO.setRole(role);
        respVO.setId(studentDO.getId());
        return respVO;
    }

}
