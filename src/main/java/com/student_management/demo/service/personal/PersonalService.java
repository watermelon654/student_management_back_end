package com.student_management.demo.service.personal;

import com.student_management.demo.controller.personal.vo.PersonalImportReqVO;
import com.student_management.demo.controller.personal.vo.PersonalImportRespVO;

import java.util.List;

public interface PersonalService {
    PersonalImportRespVO importRecord(List<PersonalImportReqVO> userList);
}
