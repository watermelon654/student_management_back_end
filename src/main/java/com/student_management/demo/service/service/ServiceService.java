package com.student_management.demo.service.service;

import com.student_management.demo.controller.grade.vo.GradeImportExcelVO;
import com.student_management.demo.controller.grade.vo.GradeImportRespVO;
import com.student_management.demo.controller.service.vo.ServiceImportReqVO;
import com.student_management.demo.controller.service.vo.ServiceImportRespVO;
import com.student_management.demo.mapper.dataobject.science.ScienceDO;
import com.student_management.demo.mapper.dataobject.service.ServiceDO;

import java.util.Collection;
import java.util.List;

public interface ServiceService {
    ServiceImportRespVO importRecord(List<ServiceImportReqVO> userList);

    List<ServiceDO> getList(Collection<Long> ids);

    List<ServiceDO> getAllList();
    List<ServiceDO> getMyList(String num);
}
