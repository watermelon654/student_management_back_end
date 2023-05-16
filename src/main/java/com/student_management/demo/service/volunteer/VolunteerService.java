package com.student_management.demo.service.volunteer;

import com.student_management.demo.controller.volunteer.vo.VolunteerImportExcelVO;
import com.student_management.demo.controller.volunteer.vo.VolunteerImportRespVO;

import java.util.List;

public interface VolunteerService {

    /**
     * 批量导入志愿服务时长
     *
     * @param importVolunteer     导入服务时长列表
     * @return 导入结果
     */
    VolunteerImportRespVO importVolunteerList(List<VolunteerImportExcelVO> importVolunteer);


}
