package com.student_management.demo.mapper.mysql.student;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.mapper.dataobject.student.StudentDO;

public interface StudentMapper extends BaseMapper {
    StudentDO findByNum(String num) ;

}
