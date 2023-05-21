package com.student_management.demo.service.student;

import cn.hutool.core.collection.CollUtil;
import com.student_management.demo.controller.student.vo.StudentImportExcelReqVO;
import com.student_management.demo.controller.student.vo.StudentImportRespVO;
import com.student_management.demo.convert.student.StudentConvert;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
@Service("studentService")
@Slf4j
public class StudentServiceImpl implements StudentService{
    @Resource
    private StudentMapper studentMapper;

    /**
     * 导入学生信息
     * @param importStudent     导入学生信息列表
     * @return
     */
    @Override
    public StudentImportRespVO importStudentList(List<StudentImportExcelReqVO> importStudent) {
        StudentImportRespVO respVO = StudentImportRespVO.builder().createsStudentnames(new ArrayList<>())
                .updateStudentnames(new ArrayList<>()).failureStudentnames(new LinkedHashMap<>()).build();
        //列表为空
        if (CollUtil.isEmpty(importStudent)) {
            respVO.setEmpty(true);
            return respVO;
        }
        //对每一个表项检查
        importStudent.forEach(student -> {
            // 判断是否在学生信息表stu_info中，在进行插入
            StudentDO existStu = studentMapper.selectStudentByNum(student.getNum());
            if (existStu == null) {
                // 如果学生表中不存在，在学生表中插入记录
                StudentDO createStudent = StudentConvert.INSTANCE.convert(student);
                studentMapper.insert(createStudent);
                respVO.getCreatesStudentnames().add(student.getName());
            } else {
                // 如果存在，更新学生表表中的记录
                StudentDO updateStudent = StudentConvert.INSTANCE.convert(student);
                updateStudent.setId(existStu.getId());
                studentMapper.updateById(updateStudent);
                respVO.getUpdateStudentnames().add(student.getName());
            }
        });
        return respVO;
    }

    /**
     * 查找所有学生信息
     * @return
     */
    @Override
    public List<StudentDO> selectALLList() {
        return studentMapper.selectStudent();
    }

}
