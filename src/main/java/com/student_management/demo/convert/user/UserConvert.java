package com.student_management.demo.convert.user;

import com.student_management.demo.controller.user.vo.UserBasicRespVO;
import com.student_management.demo.mapper.dataobject.user.UserPermissionDO;
import org.mapstruct.factory.Mappers;

public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);


}
