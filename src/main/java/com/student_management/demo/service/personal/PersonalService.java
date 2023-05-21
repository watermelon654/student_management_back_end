package com.student_management.demo.service.personal;

import com.student_management.demo.controller.personal.vo.PersonalImportReqVO;
import com.student_management.demo.controller.personal.vo.PersonalImportRespVO;
import com.student_management.demo.mapper.dataobject.personal.PersonalDO;

import java.util.Collection;
import java.util.List;

public interface PersonalService {
    PersonalImportRespVO importRecord(List<PersonalImportReqVO> userList);

    /**
     * 获得科研情况列表
     *
     * @param ids 编号
     * @return 科研情况列表
     */
    List<PersonalDO> getList(Collection<Long> ids);
}
