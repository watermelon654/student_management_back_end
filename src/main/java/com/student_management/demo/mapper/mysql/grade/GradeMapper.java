package com.student_management.demo.mapper.mysql.grade;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.controller.grade.vo.GradeRespVO;
import com.student_management.demo.controller.grade.vo.GradeScoreReqVO;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GradeMapper extends BaseMapper<GradeDO>{

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

    /**
     * 查看所有未删除学生的GPA
     *
     * @return 未删除学生列表
     */
    default List<GradeDO> selectAllStudents() {
        QueryWrapper<GradeDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isDel", 0);
        return selectList(queryWrapper);
//        return selectList(null);
    }


    /**
     * 根据学生学号更新当前学生GPA打分
     *
     * @param gradeScore
     * @return 打分结果，大于0表示打分成功，等于0表示打分失败
     */
    int updateGPAScore(GradeScoreReqVO gradeScore);

    /**
     * 根据学生学号查询当前学生是否已在grade表中删除
     *
     * @param stuNum
     * @return isDel
     */
    int isDeleted(String stuNum);

    /**
     * 根据学生学号获取当前学生GPA信息
     *
     * @param stuNum
     * @return 学号，姓名，gpa
     */
    GradeRespVO getInfoByStuNum(String stuNum);

    Integer getGpaScoreByStuNum(String stuNum);
}
