package com.student_management.demo.convert.grade;

import com.student_management.demo.controller.grade.vo.GradeCreateReqVO;
import com.student_management.demo.controller.grade.vo.GradeImportExcelVO;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GradeConvert {
    GradeConvert INSTANCE = Mappers.getMapper(GradeConvert.class);
    GradeDO convert(GradeCreateReqVO bean);
    GradeCreateReqVO convert(GradeDO bean);

    //@Mappings(
    //        {
    //                @Mapping(target = "id", defaultValue = "0l"),
    //                @Mapping(target = "stu_id", defaultValue = ""),
    //                @Mapping(target = "note", defaultValue = ""),
    //                @Mapping(target = "status", defaultValue = "0")
    //        }
    //)
    GradeDO convert(GradeImportExcelVO grade);
}
