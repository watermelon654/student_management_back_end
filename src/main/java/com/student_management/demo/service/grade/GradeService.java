package com.student_management.demo.service.grade;

import com.student_management.demo.controller.grade.vo.GradeCreateReqVO;
import com.student_management.demo.controller.grade.vo.GradeImportExcelVO;
import com.student_management.demo.controller.grade.vo.GradeImportRespVO;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;

import java.util.List;

public interface GradeService {

    /**
     * 批量导入GPA
     *
     * @param importGrade     导入GPA列表
     * @return 导入结果
     */
    GradeImportRespVO importGradeList(List<GradeImportExcelVO> importGrade);
    /**
     * 创建成绩
     *
     * @param reqVO 学生信息
     * @return 成绩记录编号
     */
    GradeDO searchGrade(GradeCreateReqVO reqVO);

    /**
     * 修改成绩
     *
     * @param reqVO 用户信息
     */
    void updateGrade(GradeCreateReqVO reqVO);

}
