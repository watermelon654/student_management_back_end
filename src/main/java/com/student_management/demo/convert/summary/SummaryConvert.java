package com.student_management.demo.convert.summary;

import com.student_management.demo.controller.summary.vo.*;
import com.student_management.demo.mapper.dataobject.summary.SummaryDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface SummaryConvert {

    SummaryConvert INSTANCE = Mappers.getMapper(SummaryConvert.class);
    SummaryDO convert(SummaryCreateReqVO bean);
    SummaryCreateReqVO convert(SummaryDO bean);
    SummaryDO convert(SummaryImportReqVO science);

    List<SummaryRespVO> convertList(List<SummaryDO> list);
    List<SummaryBaseVO> convertList2(List<SummaryDO> list);

}