package com.student_management.demo.service.volunteer;

import com.student_management.demo.controller.grade.vo.GradeBaseVO;
import com.student_management.demo.controller.volunteer.vo.*;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;
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
     * 获得全体学生志愿服务时长列表
     *
     * @return 全体学生志愿服务时长列表
     */
    VolunteerSelectListRespVO selectAllStudents();

    /**
     * 打分结果
     *
     * @param volunteerScore
     * @return 打分结果，true表示打分成功，false表示打分失败
     */
    boolean updateResult(VolunteerScoreReqVO volunteerScore);

    /**
     * 根据学生学号获取当前学生志愿服务时长信息
     *
     * @param stuNum
     * @return 当前学生学号、姓名、志愿服务时长
     */
    VolunteerRespVO getInfoByStuNum(String stuNum);

    /**
     * 将VolunteerDO复制给VolunteerBaseVO并添加score
     *
     * @param listdo
     * @return listvo
     */
    List<VolunteerBaseVO> convertList(List<VolunteerDO> listdo);

    /**
     * 根据学生学号查询当前学生是否已在volunteer表中删除
     *
     * @param stuNum
     * @return 查询结果，true表示已删除
     */
    Boolean isDeleted(String stuNum);
}
