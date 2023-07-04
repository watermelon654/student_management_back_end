package com.student_management.demo.convert.student;


import com.student_management.demo.controller.student.vo.StudentImportExcelReqVO;
import com.student_management.demo.mapper.dataobject.student.StudentBasicDO;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentConvert {
    StudentConvert INSTANCE = Mappers.getMapper(StudentConvert.class);
    StudentDO convert(StudentImportExcelReqVO bean);
    StudentBasicDO convert(StudentDO bean);
    List<StudentBasicDO> convertList(List<StudentDO> bean);

    List<StudentDO> convertList1(List<StudentImportExcelReqVO> bean);
}
