package com.student_management.demo.service.science;

import com.student_management.demo.controller.grade.vo.GradeImportExcelVO;
import com.student_management.demo.controller.grade.vo.GradeImportRespVO;
import com.student_management.demo.controller.science.vo.ScienceImportReqVO;
import com.student_management.demo.controller.science.vo.ScienceImportRespVO;

import java.util.List;

public interface ScienceService {
    ScienceImportRespVO importRecord(List<ScienceImportReqVO> userList);
}
