package com.student_management.demo.service.summary;

import cn.hutool.core.collection.CollUtil;
import com.student_management.demo.controller.summary.vo.SummaryImportReqVO;
import com.student_management.demo.controller.summary.vo.SummaryImportRespVO;
import com.student_management.demo.convert.summary.SummaryConvert;
import com.student_management.demo.mapper.dataobject.summary.SummaryDO;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import com.student_management.demo.mapper.mysql.summary.SummaryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import static com.baomidou.mybatisplus.core.toolkit.IdWorker.getId;

@Service("SummaryService")
@Validated
@Slf4j
public class SummaryServiceImpl implements SummaryService {
    @Resource
    private SummaryMapper summaryMapper;

    @Resource
    private StudentMapper studentMapper;

    public SummaryImportRespVO importRecord(List<SummaryImportReqVO> importSummary) {
        SummaryImportRespVO respVO = SummaryImportRespVO.builder().createSummarynames(new ArrayList<>())
                .updateSummarynames(new ArrayList<>()).failureSummarynames(new LinkedHashMap<>()).build();
        //列表为空
        if (CollUtil.isEmpty(importSummary)) {
            respVO.setEmpty(true);
            return respVO;
        }
        //对每一个表项检查
        importSummary.forEach(Summary -> {
            // 判断是否在学生信息表stu_info中，在进行插入
            StudentDO existStu = studentMapper.selectStudentByNum(Summary.getStu_num());
            if (existStu == null) {
                // 如果学生表中不存在，在学生表中插入记录
                studentMapper.insertStudentBasicInfo(Summary.getStu_name(),Summary.getStu_num());
            }
//             获取stu_id，判断是否在学生成绩表Summary中，在进行插入
            existStu = studentMapper.selectStudentByNum(Summary.getStu_num());
            SummaryDO existSummary = summaryMapper.selectSummaryByStuNum(Summary.getStu_num());
            if (existSummary == null) {
//             如果在成绩表中不存在，在成绩表插入记录
            SummaryDO createSummary = SummaryConvert.INSTANCE.convert(Summary);
            createSummary.setStu_id(existStu.getId());
            summaryMapper.insert(createSummary);
            respVO.getCreateSummarynames().add(Summary.getStu_name());
                return;
            }
            // 如果存在，更新成绩表中的记录
            SummaryDO updateSummary = SummaryConvert.INSTANCE.convert(Summary);
            updateSummary.setId(existSummary.getId());
            summaryMapper.updateById(updateSummary);
            respVO.getUpdateSummarynames().add(Summary.getStu_name());
        });
        return respVO;
    }
//    @Override
//    public void create(SummaryCreateReqVO createReqVO) {
//        SummaryDO  = SummaryConvert.INSTANCE.convert(createReqVO);
//        mapper.insert();
//        // 返回
//        return .getId();
//    }
//
//    @Override
//    public void update(SummaryUpdateReqVO updateReqVO) {
//        // 校验存在
////        validateExists(updateReqVO.getId());
//        // 更新
//        SummaryDO updateObj = SummaryConvert.INSTANCE.convert(updateReqVO);
//        mapper.updateById(updateObj);
//    }
}
