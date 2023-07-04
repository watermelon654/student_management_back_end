package com.student_management.demo.mapper.mysql.grade;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.controller.grade.vo.Judge.GradeExcelUpdateVO;
import com.student_management.demo.controller.grade.vo.Student.StudentGradeRespVO;
import com.student_management.demo.controller.grade.vo.Judge.GradeScoreReqVO;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GradeMapper extends BaseMapper<GradeDO>{

    //--------------------------------------
    //评委端

    Integer isDeleted(String stuNum);

    Integer isDeletedInStuinfo(String stuNum);

    /**
     * 按照学号查询GPA
     * @param stu_num
     * @return
     */
    default GradeDO selectGradeByStuNum(String stu_num) {
        QueryWrapper<GradeDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stuNum", stu_num);
        return selectOne(wrapper);
    }

    /**
     * 按照学生id查询GPA
     * @param stu_id
     * @return
     */
    default GradeDO selectGradeByStuId(Long stu_id) {
        QueryWrapper<GradeDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stuId", stu_id);
        return selectOne(wrapper);
    }

    GradeDO selectStudentById(Long id);


    /**
     * 查看所有未删除学生的GradeDO
     *
     * @return 所有未删除学生的GradeDO:学号，姓名，gpa，创建时间，更新时间
     */
    default List<GradeDO> selectAllStudents() {
        QueryWrapper<GradeDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isDel", 0);
        return selectList(queryWrapper);
//        return selectList(null);
    }

    Integer getGpaScoreByStuNum(String stuNum);

    Integer deleteByStuNum(String stuNum);

//    int updateDeleteInfo(GradeScoreReqVO gradeScore);

    /**
     * 更新当前学生gpa打分和updateUserId
     *
     * @return 更新操作受影响的行数，如果更新成功，返回的值会大于0，否则返回0
     */
    int updateGPAScore(GradeScoreReqVO gradeScore);

    int updateGradeUpdateInfo(GradeScoreReqVO gradeScore);

    int updateSummaryUpdateInfo(GradeScoreReqVO gradeScore);

    int createStuUpdateInfo(long id, String stuNum);

    int updateGradeUploadInfo(GradeExcelUpdateVO gradeExcelUpdateVO);

    //--------------------------------------
    //学生端

    /**
     * 根据学生学号获取当前学生GPA信息
     *
     * @param stuNum
     * @return 当前学生StudentGradeRespVO:学号，姓名，gpa
     */
    StudentGradeRespVO getInfoByStuNum(String stuNum);

}
