package com.student_management.demo.convert.student;

import com.student_management.demo.controller.grade.vo.GradeCreateReqVO;
import com.student_management.demo.controller.grade.vo.GradeImportExcelVO;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentConvert {
    StudentConvert INSTANCE = Mappers.getMapper(StudentConvert.class);

}
