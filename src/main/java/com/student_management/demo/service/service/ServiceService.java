package com.student_management.demo.service.service;

import com.student_management.demo.controller.grade.vo.GradeImportExcelVO;
import com.student_management.demo.controller.grade.vo.GradeImportRespVO;
import com.student_management.demo.controller.service.vo.ServiceImportReqVO;
import com.student_management.demo.controller.service.vo.ServiceImportRespVO;

import java.util.List;

public interface ServiceService {
    ServiceImportRespVO importRecord(List<ServiceImportReqVO> userList);
}
