package com.student_management.demo.service.service;

import cn.hutool.core.collection.CollUtil;
import com.student_management.demo.controller.service.vo.ServiceImportReqVO;
import com.student_management.demo.controller.service.vo.ServiceImportRespVO;
import com.student_management.demo.controller.summary.vo.SummarySelectListRespVO;
import com.student_management.demo.convert.service.ServiceConvert;
import com.student_management.demo.mapper.dataobject.service.ServiceDO;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.dataobject.summary.SummaryDO;
import com.student_management.demo.mapper.mysql.service.ServiceMapper;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import cn.hutool.core.collection.CollUtil;
import com.student_management.demo.controller.service.vo.ServiceImportReqVO;
import com.student_management.demo.controller.service.vo.ServiceImportRespVO;
import com.student_management.demo.controller.summary.vo.SummarySelectListRespVO;
import com.student_management.demo.convert.service.ServiceConvert;
import com.student_management.demo.mapper.dataobject.service.ServiceDO;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.dataobject.summary.SummaryDO;
import com.student_management.demo.mapper.mysql.service.ServiceMapper;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

@Service("ServiceService")
@Slf4j
public class ServiceServiceImpl implements ServiceService{
    @Resource
    private ServiceMapper serviceMapper;

    @Resource
    private StudentMapper studentMapper;

    public ServiceImportRespVO importRecord(List<ServiceImportReqVO> importService) {
        ServiceImportRespVO respVO = ServiceImportRespVO.builder().createServicenames(new ArrayList<>())
                .updateServicenames(new ArrayList<>()).failureServicenames(new LinkedHashMap<>()).build();
        //列表为空
        if (CollUtil.isEmpty(importService)) {
            respVO.setEmpty(true);
            return respVO;
        }
        //对每一个表项检查
        importService.forEach(Service -> {
            // 判断是否在学生信息表stu_info中，在进行插入
            StudentDO existStu = studentMapper.selectStudentByNum(Service.getStuNum());
            if (existStu == null) {
                // 如果学生表中不存在，在学生表中插入记录
                studentMapper.insertStudentBasicInfo(Service.getStuName(),Service.getStuNum());
            }
            // 获取stu_id，判断是否在学生成绩表Service中，在进行插入
            existStu = studentMapper.selectStudentByNum(Service.getStuNum());
            // ServiceDO existService = serviceMapper.selectServiceByStuNum(Service.getStuNum());
//            if (existService == null) {
            // 如果在成绩表中不存在，在成绩表插入记录
            ServiceDO createService = ServiceConvert.INSTANCE.convert(Service);
            createService.setStuId(existStu.getId());
            serviceMapper.insert(createService);
            respVO.getCreateServicenames().add(Service.getStuName());
//                return;
//            }
//            // 如果存在，更新成绩表中的记录
//            ServiceDO updateService = ServiceConvert.INSTANCE.convert(Service);
//            updateService.setId(existService.getId());
//            serviceMapper.updateById(updateService);
//            respVO.getUpdateServicenames().add(Service.getStu_name());
        });
        return respVO;
    }
    @Override
    public List<ServiceDO> getList(Collection<Long> ids) {
        System.out.println(serviceMapper.selectBatchIds(ids));
        return serviceMapper.selectBatchIds(ids);
    }

    @Override
    public List<ServiceDO> getAllList() {
        return serviceMapper.selectAllList();
    }


}
