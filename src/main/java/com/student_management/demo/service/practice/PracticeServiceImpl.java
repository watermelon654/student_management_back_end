package com.student_management.demo.service.practice;

import cn.hutool.core.collection.CollUtil;
import com.student_management.demo.controller.practice.vo.PracticeImportReqVO;
import com.student_management.demo.controller.practice.vo.PracticeImportRespVO;
import com.student_management.demo.convert.practice.PracticeConvert;
import com.student_management.demo.mapper.dataobject.practice.PracticeDO;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.mysql.practice.PracticeMapper;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service("PracticeService")
@Slf4j
public class PracticeServiceImpl implements PracticeService{
    @Resource
    private PracticeMapper practiceMapper;

    @Resource
    private StudentMapper studentMapper;

    public PracticeImportRespVO importRecord(List<PracticeImportReqVO> importPractice) {
        PracticeImportRespVO respVO = PracticeImportRespVO.builder().createPracticenames(new ArrayList<>())
                .updatePracticenames(new ArrayList<>()).failurePracticenames(new LinkedHashMap<>()).build();
        //列表为空
        if (CollUtil.isEmpty(importPractice)) {
            respVO.setEmpty(true);
            return respVO;
        }
        //对每一个表项检查
        importPractice.forEach(Practice -> {
            // 判断是否在学生信息表stu_info中，在进行插入
            StudentDO existStu = studentMapper.selectStudentByNum(Practice.getStuNum());
            if (existStu == null) {
                // 如果学生表中不存在，在学生表中插入记录
                studentMapper.insertStudentBasicInfo(Practice.getStuName(),Practice.getStuNum());
            }
            // 获取stu_id，判断是否在学生成绩表Science中，在进行插入
            existStu = studentMapper.selectStudentByNum(Practice.getStuNum());
            PracticeDO existPractice = practiceMapper.selectPracticeByStuNum(Practice.getStuNum());
            if (existPractice == null) {
                // 如果在成绩表中不存在，在成绩表插入记录
                PracticeDO createPractice = PracticeConvert.INSTANCE.convert(Practice);
                createPractice.setStuId(existStu.getId());
                practiceMapper.insertPractice(createPractice.getStuId(), createPractice.getStuNum(),createPractice.getStuName(), createPractice.getTitle(),createPractice.getDirector(),createPractice.getConstitution(),createPractice.getContent(),createPractice.getTime(),createPractice.getResult(),createPractice.getScore(),createPractice.getStatus());
                System.out.println(createPractice);
                respVO.getCreatePracticenames().add(Practice.getStuName());
                return;
            }
            // 如果存在，更新成绩表中的记录
            PracticeDO updatePractice = PracticeConvert.INSTANCE.convert(Practice);
            updatePractice.setId(existPractice.getId());
            practiceMapper.updateById(updatePractice);
            respVO.getUpdatePracticenames().add(Practice.getStuName());
        });
        return respVO;
    }
}
