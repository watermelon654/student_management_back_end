package com.student_management.demo.service.student;

import com.student_management.demo.controller.student.vo.StudentImportExcelReqVO;
import com.student_management.demo.controller.student.vo.StudentImportRespVO;
import com.student_management.demo.mapper.dataobject.student.StudentBasicDO;

import java.util.List;

public interface StudentService {
    /**
     * 批量导入学生信息
     *
     * @param importGrade     导入学生信息列表
     * @return 导入结果
     */
    StudentImportRespVO importStudentList(List<StudentImportExcelReqVO> importGrade, String id);

    List<StudentBasicDO> selectALLList();
}
