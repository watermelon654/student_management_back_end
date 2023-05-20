package com.student_management.demo.mapper.mysql.student;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.controller.stu.vo.StudentVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMapper1 extends BaseMapper<StudentVo> {


    default StudentVo selectStudentByNum(String num) {
        QueryWrapper<StudentVo> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("num", num);
        return selectOne(wrapper);
    }



    default int updateStu (StudentVo studentVo){
        QueryWrapper<StudentVo> wrapper = new QueryWrapper<>();
        wrapper.eq("id",studentVo.getId());
        return update(studentVo,wrapper);
    }

}
