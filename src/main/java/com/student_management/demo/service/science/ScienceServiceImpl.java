package com.student_management.demo.service.science;

import cn.hutool.core.collection.CollUtil;
import com.student_management.demo.controller.science.vo.ScienceImportReqVO;
import com.student_management.demo.controller.science.vo.ScienceImportRespVO;
import com.student_management.demo.controller.summary.vo.SummarySelectListRespVO;
import com.student_management.demo.convert.science.ScienceConvert;
import com.student_management.demo.mapper.dataobject.science.ScienceDO;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.dataobject.summary.SummaryDO;
import com.student_management.demo.mapper.mysql.science.ScienceMapper;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

@Service("ScienceService")
@Slf4j
public class ScienceServiceImpl implements ScienceService{
    @Resource
    private ScienceMapper scienceMapper;

    @Resource
    private StudentMapper studentMapper;

    public ScienceImportRespVO importRecord(List<ScienceImportReqVO> importScience) {
        ScienceImportRespVO respVO = ScienceImportRespVO.builder().createSciencenames(new ArrayList<>())
                .updateSciencenames(new ArrayList<>()).failureSciencenames(new LinkedHashMap<>()).build();
        //列表为空
        if (CollUtil.isEmpty(importScience)) {
            respVO.setEmpty(true);
            return respVO;
        }
        //对每一个表项检查
        importScience.forEach(Science -> {
            // 判断是否在学生信息表stu_info中，在进行插入
            StudentDO existStu = studentMapper.selectStudentByNum(Science.getStuNum());
            if (existStu == null) {
                // 如果学生表中不存在，在学生表中插入记录
                studentMapper.insertStudentBasicInfo(Science.getStuName(),Science.getStuNum());
            }
            // 获取stu_id，判断是否在学生成绩表Science中，在进行插入
            existStu = studentMapper.selectStudentByNum(Science.getStuNum());
            // ScienceDO existScience = scienceMapper.selectScienceByStuNum(Science.getStuNum());
//            if (existScience == null) {
                // 如果在成绩表中不存在，在成绩表插入记录
                ScienceDO createScience = ScienceConvert.INSTANCE.convert(Science);
                createScience.setStuId(existStu.getId());
                scienceMapper.insert(createScience);
                respVO.getCreateSciencenames().add(Science.getStuName());
//                return;
//            }
//            // 如果存在，更新成绩表中的记录
//            ScienceDO updateScience = ScienceConvert.INSTANCE.convert(Science);
//            updateScience.setId(existScience.getId());
//            scienceMapper.updateById(updateScience);
//            respVO.getUpdateSciencenames().add(Science.getStu_name());
        });
        return respVO;
    }
    @Override
    public List<ScienceDO> getList(Collection<Long> ids) {
        System.out.println(scienceMapper.selectBatchIds(ids));
        return scienceMapper.selectBatchIds(ids);
    }

    @Override
    public List<ScienceDO> getAllList() {
        return scienceMapper.selectAllList();
    }

    @Override
    public List<ScienceDO> getMyList(String num) {
        return scienceMapper.selectScienceByStuNum(num);
    }
}
