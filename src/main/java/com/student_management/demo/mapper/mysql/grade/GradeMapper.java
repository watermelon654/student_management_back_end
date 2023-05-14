package com.student_management.demo.mapper.mysql.grade;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
        wrapper.eq("stu_num", stu_num);
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
        wrapper.eq("stu_id", stu_id);
        return selectOne(wrapper);
    }

}
