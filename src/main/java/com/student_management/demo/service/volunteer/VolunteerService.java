package com.student_management.demo.service.volunteer;

import com.student_management.demo.controller.volunteer.vo.VolunteerImportExcelVO;
import com.student_management.demo.controller.volunteer.vo.VolunteerImportRespVO;
import com.student_management.demo.controller.volunteer.vo.VolunteerSelectListRespVO;
import com.student_management.demo.mapper.dataobject.volunteer.VolunteerDO;

import java.util.Collection;
import java.util.List;

public interface VolunteerService {

    /**
     * 批量导入志愿服务时长
     *
     * @param importVolunteer     导入服务时长列表
     * @return 导入结果
     */
    VolunteerImportRespVO importVolunteerList(List<VolunteerImportExcelVO> importVolunteer);

    /**
     * 根据id获得志愿服务时长列表
     *
     * @param ids 编号
     * @return 志愿服务时长列表
     */
    List<VolunteerDO> getList(Collection<Long> ids);

    /**
     * 获得全体学生志愿服务时长列表
     *
     * @return 全体学生志愿服务时长列表
     */
    VolunteerSelectListRespVO selectAllStudents();
}
