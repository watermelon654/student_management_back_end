package com.student_management.demo.mapper.mysql.grade;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.controller.grade.vo.GradeRespVO;
import com.student_management.demo.controller.volunteer.vo.VolunteerRespVO;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;
import com.student_management.demo.mapper.dataobject.volunteer.VolunteerDO;
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
     * 查看全部学生GPA
     *
     * @return 全部学生列表
     */
    default List<GradeDO> selectAllStudents() {
        return selectList(null);
    }

    /**
     * 打分结果
     *
     * @param grade
     * @return 打分结果，大于0表示打分成功，等于0表示打分失败
     */
    int updateByStuNum(GradeDO grade);


    /**
     * 根据学生id获取当前学生志愿服务时长信息
     *
     * @param stuId
     * @return 学号。姓名。gpa
     */
    GradeRespVO getInfoByStuId(Long stuId);
}
