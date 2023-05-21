package com.student_management.demo.convert.service;


import com.student_management.demo.controller.service.vo.ServiceRespVO;
import com.student_management.demo.controller.service.vo.ServiceCreateReqVO;
import com.student_management.demo.controller.service.vo.ServiceImportReqVO;

import com.student_management.demo.mapper.dataobject.service.ServiceDO;
import com.student_management.demo.mapper.dataobject.service.ServiceDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ServiceConvert {
    ServiceConvert INSTANCE = Mappers.getMapper(ServiceConvert.class);
    ServiceDO convert(ServiceCreateReqVO bean);
    ServiceCreateReqVO convert(ServiceDO bean);
    ServiceDO convert(ServiceImportReqVO service);

    List<ServiceRespVO> convertList(List<ServiceDO> list);

}
