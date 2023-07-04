package com.student_management.demo.service.grade;

import com.student_management.demo.controller.grade.vo.Judge.*;
import com.student_management.demo.controller.grade.vo.Student.StudentGradeRespVO;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;

import java.util.List;
public interface GradeService {

    //--------------------------------------
    //评委端

    /**
     * 根据学生学号查询当前学生是否已在grade表中删除
     *
     * @param stuNum
     * @return 查询结果，true表示已删除
     */
    Boolean isDeleted(String stuNum);

    Boolean isDeletedInStuinfo(String stuNum);

    /**
     * 批量导入GPA
     *
     * @param importGrade     导入GPA列表
     * @return 导入结果
     */
    GradeImportRespVO importGradeList(List<GradeImportExcelVO> importGrade, String judgeNum);

    /**
     * 获得全体未删除学生的GPA
     *
     * @return 全体未删除学生的GPA
     */
    GradeSelectListRespVO selectAllStudents();

    /**
     * 将GradeDO复制给GradeBaseVO并添加score
     *
     * @param listdo
     * @return listvo
     */
    List<JudgeGradeRespVO> convertList(List<GradeDO> listdo);

    /**
     * 打分结果
     *
     * @param gradeScore
     * @return 打分结果，true表示打分成功，false表示打分失败
     */
    boolean updateResult(GradeScoreReqVO gradeScore);

    boolean showDeleteResult(String judgeNum, String stuNum);


    //--------------------------------------
    //学生端

    /**
     * 根据学生学号获取当前学生学号、姓名、GPA
     *
     * @param stuNum
     * @return 当前学生学号、姓名、GPA
     */
    StudentGradeRespVO getInfoByStuNum(String stuNum);

    }
