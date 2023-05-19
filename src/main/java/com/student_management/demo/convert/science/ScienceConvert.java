package com.student_management.demo.convert.science;


import com.student_management.demo.controller.science.vo.ScienceCreateReqVO;
import com.student_management.demo.controller.science.vo.ScienceImportReqVO;

import com.student_management.demo.controller.science.vo.ScienceRespVO;
import com.student_management.demo.mapper.dataobject.science.ScienceDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ScienceConvert {
    ScienceConvert INSTANCE = Mappers.getMapper(ScienceConvert.class);
    ScienceDO convert(ScienceCreateReqVO bean);
    ScienceCreateReqVO convert(ScienceDO bean);
    ScienceDO convert(ScienceImportReqVO science);

    List<ScienceRespVO> convertList(List<ScienceDO> list);
}
