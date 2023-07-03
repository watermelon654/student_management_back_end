package com.student_management.demo.service.summary;

import com.student_management.demo.common.CommonResult;
import com.student_management.demo.controller.summary.vo.*;
import com.student_management.demo.mapper.dataobject.summary.SummaryDO;

import java.util.List;

public interface SummaryService {
    SummaryImportRespVO importRecord(List<SummaryImportReqVO> userList);

    SummarySelectListRespVO selectListByStatus(boolean status);

    SummarySelectListRespVO selectAllList();

}
