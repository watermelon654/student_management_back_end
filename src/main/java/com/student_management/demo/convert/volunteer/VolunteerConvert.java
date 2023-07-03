package com.student_management.demo.convert.volunteer;

import com.student_management.demo.controller.volunteer.vo.Judge.VolunteerCreateReqVO;
import com.student_management.demo.controller.volunteer.vo.Judge.VolunteerImportExcelVO;
import com.student_management.demo.mapper.dataobject.volunteer.VolunteerDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VolunteerConvert {
    VolunteerConvert INSTANCE = Mappers.getMapper(VolunteerConvert.class);
    VolunteerDO convert(VolunteerCreateReqVO bean);
    VolunteerCreateReqVO convert(VolunteerDO bean);
    VolunteerDO convert(VolunteerImportExcelVO grade);

    //List<StudentVolunteerRespVO> convertList(List<VolunteerDO> list);

}
