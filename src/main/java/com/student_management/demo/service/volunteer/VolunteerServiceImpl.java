package com.student_management.demo.service.volunteer;
import cn.hutool.core.collection.CollUtil;
import com.student_management.demo.controller.summary.vo.SummarySelectListRespVO;
import com.student_management.demo.controller.volunteer.vo.*;
import com.student_management.demo.convert.volunteer.VolunteerConvert;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.dataobject.volunteer.VolunteerDO;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import com.student_management.demo.mapper.mysql.volunteer.VolunteerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

@Service("volunteerService")
@Slf4j
public class VolunteerServiceImpl implements VolunteerService {
    @Resource
    private VolunteerMapper volunteerMapper;

    @Resource
    private StudentMapper studentMapper;


    /**
     * 批量导入服务时长，如果已经存在则强制更新
     *
     * @param importVolunteer     导入服务时长列表
     * @return 导入结果
     */
    public VolunteerImportRespVO importVolunteerList(List<VolunteerImportExcelVO> importVolunteer) {
        VolunteerImportRespVO respVO = VolunteerImportRespVO.builder().createVolunteernames(new ArrayList<>())
                .updateVolunteernames(new ArrayList<>()).failureVolunteernames(new LinkedHashMap<>()).build();
        //列表为空
        if (CollUtil.isEmpty(importVolunteer)) {
            respVO.setEmpty(true);
            return respVO;
        }
        //对每一个表项检查
        importVolunteer.forEach(volunteer -> {
            // 判断是否在学生信息表stu_info中，在进行插入
            StudentDO existStu = studentMapper.selectStudentByNum(volunteer.getStuNum());
            if (existStu == null) {
                // 如果学生表中不存在，在学生表中插入记录
                studentMapper.insertStudentBasicInfo(volunteer.getStuName(),volunteer.getStuNum());
            }
            // 获取stu_id，判断是否在学生成绩表grade中，在进行插入
            existStu = studentMapper.selectStudentByNum(volunteer.getStuNum());
            VolunteerDO existVolunteer = volunteerMapper.selectVolunteerByStuNum(volunteer.getStuNum());
            if (existVolunteer == null) {
                // 如果在成绩表中不存在，在成绩表插入记录
                VolunteerDO createVolunteer = VolunteerConvert.INSTANCE.convert(volunteer);
                createVolunteer.setStuId(existStu.getId());
                volunteerMapper.insert(createVolunteer);
                respVO.getCreateVolunteernames().add(volunteer.getStuName());
                return;
            }
            // 如果存在，更新成绩表中的记录
            VolunteerDO updateVolunteer = VolunteerConvert.INSTANCE.convert(volunteer);
            updateVolunteer.setId(existVolunteer.getId());
            volunteerMapper.updateById(updateVolunteer);
            respVO.getUpdateVolunteernames().add(volunteer.getStuName());
        });
        return respVO;
    }

//    @Override
//    public List<VolunteerDO> getList(Collection<Long> ids) {
//        System.out.println(volunteerMapper.selectBatchIds(ids));
//        return volunteerMapper.selectBatchIds(ids);
//    }

    /**
     * 获得全体学生志愿服务时长列表
     *
     * @return 全体学生志愿服务时长列表
     */
    @Override
    public VolunteerSelectListRespVO selectAllStudents() {
        List<VolunteerDO> listdo = volunteerMapper.selectAllStudents();
        VolunteerSelectListRespVO respVO = new VolunteerSelectListRespVO();
        List<VolunteerBaseVO> listvo = VolunteerConvert.INSTANCE.convertList2(listdo);
        respVO.setVolunteerlist(listvo);
        return respVO;
    }

    /**
     * 打分结果
     *
     * @param volunteer
     * @return 打分结果，true表示打分成功，false表示打分失败
     */
    public boolean updateResult(VolunteerDO volunteer) {
        return volunteerMapper.updateByStuNum(volunteer) > 0;
    }

    /**
     * 根据学生id获取当前学生志愿服务时长信息
     *
     * @param stuId
     * @return 当前学生志愿服务时长信息
     */
    public VolunteerRespVO getInfoByStuId(Long stuId) {
        return volunteerMapper.getInfoByStuId(stuId);
    }
}
