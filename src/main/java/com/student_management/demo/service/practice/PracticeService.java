package com.student_management.demo.service.practice;

import com.student_management.demo.controller.practice.vo.PracticeImportReqVO;
import com.student_management.demo.controller.practice.vo.PracticeImportRespVO;
import com.student_management.demo.mapper.dataobject.practice.PracticeDO;

import java.util.Collection;
import java.util.List;

public interface PracticeService {
    PracticeImportRespVO importRecord(List<PracticeImportReqVO> userList);


    List<PracticeDO> getList(Collection<Long> ids);

    List<PracticeDO> getAllList();
}
