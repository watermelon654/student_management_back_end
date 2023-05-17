package com.student_management.demo.convert.practice;


import com.student_management.demo.controller.practice.vo.PracticeCreateReqVO;
import com.student_management.demo.controller.practice.vo.PracticeImportReqVO;

import com.student_management.demo.mapper.dataobject.practice.PracticeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PracticeConvert {
    PracticeConvert INSTANCE = Mappers.getMapper(PracticeConvert.class);
    PracticeDO convert(PracticeCreateReqVO bean);
    PracticeCreateReqVO convert(PracticeDO bean);
    PracticeDO convert(PracticeImportReqVO practice);
}
