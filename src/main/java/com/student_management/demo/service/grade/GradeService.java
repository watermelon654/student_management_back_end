package com.student_management.demo.service.grade;

import com.student_management.demo.controller.grade.vo.*;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;
import org.springframework.stereotype.Service;

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
     * 获得全体学生GPA
     *
     * @return 全体学生GPA
     */
    GradeSelectListRespVO selectAllStudents();

    /**
     * 打分结果
     *
     * @param grade
     * @return 打分结果，true表示打分成功，false表示打分失败
     */
    boolean updateResult(GradeDO grade);

    /**
     * 根据学生id获取当前学生志愿服务时长信息
     *
     * @param stuNum
     * @return 当前学生志愿服务时长信息
     */
    GradeRespVO getInfoByStuNum(String stuNum);
}
