package com.student_management.demo.service.grade;

import com.student_management.demo.controller.grade.vo.*;
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
     * 获得全体学生GPA
     *
     * @return 全体学生GPA
     */
    GradeSelectListRespVO selectAllStudents();

    /**
     * 打分结果
     *
     * @param gradeScore
     * @return 打分结果，true表示打分成功，false表示打分失败
     */
    boolean updateResult(GradeScoreReqVO gradeScore);

    /**
     * 根据学生学号获取当前学生学号、姓名、GPA
     *
     * @param stuNum
     * @return 当前学生学号、姓名、GPA
     */
    GradeRespVO getInfoByStuNum(String stuNum);

    /**
     * 将GradeDO复制给GradeBaseVO并添加score
     *
     * @param listdo
     * @return listvo
     */
    List<GradeBaseVO> convertList(List<GradeDO> listdo);

    /**
     * 根据学生学号查询当前学生是否已在grade表中删除
     *
     * @param stuNum
     * @return 查询结果，true表示已删除
     */
    Boolean isDeleted(String stuNum);
}
