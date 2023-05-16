package com.student_management.demo.convert.personal;

import com.student_management.demo.controller.personal.vo.PersonalCreateReqVO;
import com.student_management.demo.controller.personal.vo.PersonalImportReqVO;
import com.student_management.demo.mapper.dataobject.personal.PersonalDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonalConvert {
    PersonalConvert INSTANCE = Mappers.getMapper(PersonalConvert.class);
    PersonalDO convert(PersonalCreateReqVO bean);
    PersonalCreateReqVO convert(PersonalDO bean);
    PersonalDO convert(PersonalImportReqVO personal);
}
