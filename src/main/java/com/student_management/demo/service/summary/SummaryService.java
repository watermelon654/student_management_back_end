package com.student_management.demo.service.summary;

import com.student_management.demo.controller.science.vo.ScienceImportReqVO;
import com.student_management.demo.controller.science.vo.ScienceImportRespVO;
import com.student_management.demo.controller.summary.vo.*;

import javax.validation.Valid;
import java.util.List;

public interface SummaryService {
    SummaryImportRespVO importRecord(List<SummaryImportReqVO> userList);
    /**
     * 创建学生评分
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
//    Long create(@Valid SummaryCreateReqVO createReqVO);

    /**
     * 更新学生评分
     *
     * @param updateReqVO 更新信息
     */
//    void update(@Valid SummaryUpdateReqVO updateReqVO);
}
