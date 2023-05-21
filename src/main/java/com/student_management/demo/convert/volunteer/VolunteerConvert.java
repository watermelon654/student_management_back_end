package com.student_management.demo.convert.volunteer;

import com.student_management.demo.controller.science.vo.ScienceRespVO;
import com.student_management.demo.controller.summary.vo.SummaryBaseVO;
import com.student_management.demo.controller.volunteer.vo.VolunteerBaseVO;
import com.student_management.demo.controller.volunteer.vo.VolunteerCreateReqVO;
import com.student_management.demo.controller.volunteer.vo.VolunteerImportExcelVO;
import com.student_management.demo.controller.volunteer.vo.VolunteerRespVO;
import com.student_management.demo.mapper.dataobject.summary.SummaryDO;
import com.student_management.demo.mapper.dataobject.volunteer.VolunteerDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VolunteerConvert {
    VolunteerConvert INSTANCE = Mappers.getMapper(VolunteerConvert.class);
    VolunteerDO convert(VolunteerCreateReqVO bean);
    VolunteerCreateReqVO convert(VolunteerDO bean);
    VolunteerDO convert(VolunteerImportExcelVO grade);

    List<VolunteerRespVO> convertList(List<VolunteerDO> list);

    List<VolunteerBaseVO> convertList2(List<VolunteerDO> list);
}
