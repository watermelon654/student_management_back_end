package com.student_management.demo.service.summary;

import com.student_management.demo.controller.science.vo.ScienceImportReqVO;
import com.student_management.demo.controller.science.vo.ScienceImportRespVO;
import com.student_management.demo.controller.summary.vo.*;
import com.student_management.demo.mapper.dataobject.summary.SummaryDO;

import javax.validation.Valid;
import java.util.List;

public interface SummaryService {
    SummaryImportRespVO importRecord(List<SummaryImportReqVO> userList);

    SummarySelectListRespVO selectListByStatus(boolean status);

    int updateGpaByStuNum(SummaryDO summary);

    int updateVolByStuNum(SummaryDO summary);
}
