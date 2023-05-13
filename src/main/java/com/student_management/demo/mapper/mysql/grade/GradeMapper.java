package com.student_management.demo.mapper.mysql.grade;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface GradeMapper extends BaseMapper<GradeDO>{
    /**
     * 插入grade
     * @param grade
     * @return res
     */
    //default int insertGrade(GradeDO grade) {
    //    insert()
    //    return res;
    //}

    /**
     * 按照学号查询GPA
     * @param stu_num
     * @return
     */
    default GradeDO selectGradeByNum(String stu_num) {
        QueryWrapper<GradeDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stu_num", stu_num);
        return selectOne(wrapper);
    }

}
