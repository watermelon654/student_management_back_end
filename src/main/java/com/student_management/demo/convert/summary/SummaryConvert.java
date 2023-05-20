package com.student_management.demo.convert.summary;

import com.student_management.demo.controller.summary.vo.SummaryCreateReqVO;
import com.student_management.demo.controller.summary.vo.SummaryImportReqVO;
import com.student_management.demo.controller.summary.vo.SummaryRespVO;
import com.student_management.demo.controller.summary.vo.SummaryUpdateReqVO;
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

//
//    PageResult<RespVO> convertPage(PageResult<DO> page);
//
//    List<ExcelVO> convertList02(List<DO> list);

}