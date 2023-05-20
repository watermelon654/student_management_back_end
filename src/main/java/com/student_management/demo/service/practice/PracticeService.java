package com.student_management.demo.service.practice;

import com.student_management.demo.controller.grade.vo.GradeImportExcelVO;
import com.student_management.demo.controller.grade.vo.GradeImportRespVO;
import com.student_management.demo.controller.practice.vo.PracticeImportReqVO;
import com.student_management.demo.controller.practice.vo.PracticeImportRespVO;

import java.util.List;

public interface PracticeService {
    PracticeImportRespVO importRecord(List<PracticeImportReqVO> userList);
}
