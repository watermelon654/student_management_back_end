package com.student_management.demo.service.personal;

import cn.hutool.core.collection.CollUtil;
import com.student_management.demo.controller.personal.vo.PersonalImportReqVO;
import com.student_management.demo.controller.personal.vo.PersonalImportRespVO;
import com.student_management.demo.convert.personal.PersonalConvert;
import com.student_management.demo.mapper.dataobject.personal.PersonalDO;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.mysql.personal.PersonalMapper;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service("PersonalService")
@Slf4j
public class PersonalServiceImpl implements PersonalService {
    @Resource
    private PersonalMapper personalMapper;

    @Resource
    private StudentMapper studentMapper;

    public PersonalImportRespVO importRecord(List<PersonalImportReqVO> importPersonal) {
        PersonalImportRespVO respVO = PersonalImportRespVO.builder().createPersonalnames(new ArrayList<>())
                .updatePersonalnames(new ArrayList<>()).failurePersonalnames(new LinkedHashMap<>()).build();
        //列表为空
        if (CollUtil.isEmpty(importPersonal)) {
            respVO.setEmpty(true);
            return respVO;
        }
        //对每一个表项检查
        importPersonal.forEach(Personal -> {
            // 判断是否在学生信息表stu_info中，在进行插入
            StudentDO existStu = studentMapper.selectStudentByNum(Personal.getStu_num());
            if (existStu == null) {
                // 如果学生表中不存在，在学生表中插入记录
                studentMapper.insertStudentBasicInfo(Personal.getStu_name(),Personal.getStu_num());
            }
            // 获取stu_id，判断是否在学生成绩表Personal中，在进行插入
            existStu = studentMapper.selectStudentByNum(Personal.getStu_num());
            PersonalDO existPersonal = personalMapper.selectPersonalByStuNum(Personal.getStu_num());
            if (existPersonal == null) {
                // 如果在成绩表中不存在，在成绩表插入记录
                PersonalDO createPersonal = PersonalConvert.INSTANCE.convert(Personal);
                createPersonal.setStu_id(existStu.getId());
                personalMapper.insert(createPersonal);
                respVO.getCreatePersonalnames().add(Personal.getStu_name());
                return;
            }
            // 如果存在，更新成绩表中的记录
            PersonalDO updatePersonal = PersonalConvert.INSTANCE.convert(Personal);
            updatePersonal.setId(existPersonal.getId());
            personalMapper.updateById(updatePersonal);
            respVO.getUpdatePersonalnames().add(Personal.getStu_name());
        });
        return respVO;
    }
}
