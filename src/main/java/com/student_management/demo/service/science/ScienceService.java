package com.student_management.demo.service.science;

import com.student_management.demo.controller.grade.vo.GradeImportExcelVO;
import com.student_management.demo.controller.grade.vo.GradeImportRespVO;
import com.student_management.demo.controller.science.vo.ScienceImportReqVO;
import com.student_management.demo.controller.science.vo.ScienceImportRespVO;
import com.student_management.demo.mapper.dataobject.science.ScienceDO;

import java.util.Collection;
import java.util.List;

public interface ScienceService {
    ScienceImportRespVO importRecord(List<ScienceImportReqVO> userList);

    /**
     * 获得科研情况列表
     *
     * @param ids 编号
     * @return 科研情况列表
     */
    List<ScienceDO> getList(Collection<Long> ids);

    List<ScienceDO> getAllList();

    List<ScienceDO> getMyList(String num);
}
