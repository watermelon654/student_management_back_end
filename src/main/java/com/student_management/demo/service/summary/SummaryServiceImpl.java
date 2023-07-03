package com.student_management.demo.service.summary;

import cn.hutool.core.collection.CollUtil;
import com.student_management.demo.common.CommonResult;
import com.student_management.demo.controller.summary.vo.SummaryDeleteReqVO;
import com.student_management.demo.controller.summary.vo.SummaryImportReqVO;
import com.student_management.demo.controller.summary.vo.SummaryImportRespVO;
import com.student_management.demo.controller.summary.vo.SummarySelectListRespVO;
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
            StudentDO existStu = studentMapper.selectStudentByNum(Summary.getStuNum());
            if (existStu == null) {
                // 如果学生表中不存在，在学生表中插入记录
                studentMapper.insertStudentBasicInfo(Summary.getStuName(),Summary.getStuNum());
            }
//             获取stu_id，判断是否在学生成绩表Summary中，在进行插入
            existStu = studentMapper.selectStudentByNum(Summary.getStuNum());
            SummaryDO existSummary = summaryMapper.selectSummaryByStuNum(Summary.getStuNum());
            if (existSummary == null) {
//             如果在成绩表中不存在，在成绩表插入记录
            SummaryDO createSummary = SummaryConvert.INSTANCE.convert(Summary);
            createSummary.setStuId(existStu.getId());
            summaryMapper.insert(createSummary);
            respVO.getCreateSummarynames().add(Summary.getStuName());
                return;
            }
            // 如果存在，更新成绩表中的记录
            SummaryDO updateSummary = SummaryConvert.INSTANCE.convert(Summary);
            updateSummary.setId(existSummary.getId());
            summaryMapper.updateById(updateSummary);
            respVO.getUpdateSummarynames().add(Summary.getStuName());
        });
        return respVO;
    }

    /**
     * 根据状态挑选学生列表
     * @param status
     * @return
     */
    @Override
    public SummarySelectListRespVO selectListByStatus(boolean status) {
        refreshStatus();
        List<SummaryDO> listdo = summaryMapper.selectListByStatus(status);
        if (listdo.size() == 0){
            return null;
        }
        System.out.println(listdo);
        SummarySelectListRespVO respVO = new SummarySelectListRespVO();
        respVO.setSummarylist(listdo);
        return respVO;
    }

    @Override
    public SummarySelectListRespVO selectAllList() {
        List<SummaryDO> listdo = summaryMapper.selectAllList();
        if (listdo.size() == 0){
            return null;
        }
        SummarySelectListRespVO respVO = new SummarySelectListRespVO();
        respVO.setSummarylist(listdo);
        return respVO;
    }

    /**
     * 更新状态为false的表项
     * @param
     * @return
     */
    private void refreshStatus() {
        //检查每个状态为false的表项是否有更新
        List<SummaryDO> listdo = summaryMapper.selectListByStatus(false);
        listdo.forEach(summary -> {
            if (summary.getGpa() != null && summary.getPer() != null &&
                    summary.getSer() != null && summary.getSci() != null &&
                    summary.getPra() != null && summary.getVol() != null) {
                summary.setStatus(true);
                //更新表项状态
                summaryMapper.updateById(summary);
            }
        });

    }

}
