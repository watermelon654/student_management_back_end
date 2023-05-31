package com.student_management.demo.mapper.mysql.student;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface StudentMapper extends BaseMapper<StudentDO> {
    /**
     * 按照学号查询学生id
     * @param num
     * @return
     */
    default StudentDO selectStudentByNum(String num) {
        QueryWrapper<StudentDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("num", num);
        return selectOne(wrapper);
    }

    /**
     * 查询所有学生
     * @param
     * @return
     */
    default List<StudentDO> selectStudent() {
        QueryWrapper<StudentDO> wrapper = new QueryWrapper<>();
        //查询条件：未被删除的记录
        wrapper.eq("isDel", 0);
        return selectList(wrapper);
    }

    /**
     * 插入学生基本信息用于生成学生id
     * @param name 姓名
     * @param num 学号
     * @return
     */
    int insertStudentBasicInfo(String name,String num);

    /**
     * 根据学生id获取学年名
     * @param id
     * @return
     */
    String selectYearNameById(Long id);

    /**
     * 根据学生id获取专业名
     * @param id
     * @return
     */
    String selectMajorNameById(Long id);

    /**
     * 根据学生id获取学苑名
     * @param id
     * @return
     */
    String selectClassNameById(Long id);

}
