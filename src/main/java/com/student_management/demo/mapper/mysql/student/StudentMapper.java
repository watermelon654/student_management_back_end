package com.student_management.demo.mapper.mysql.student;

import com.student_management.demo.mapper.dataobject.student.StudentDO;

public interface StudentMapper {
    StudentDO findByNum(String num) ;

}
