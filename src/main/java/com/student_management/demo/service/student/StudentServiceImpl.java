package com.student_management.demo.service.student;

import cn.hutool.core.collection.CollUtil;
import com.student_management.demo.controller.student.vo.StudentImportExcelReqVO;
import com.student_management.demo.controller.student.vo.StudentImportRespVO;
import com.student_management.demo.convert.student.StudentConvert;
import com.student_management.demo.mapper.dataobject.student.StudentBasicDO;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.mysql.student.ClassMapper;
import com.student_management.demo.mapper.mysql.student.MajorMapper;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import com.student_management.demo.mapper.mysql.student.YearMapper;
import com.student_management.demo.service.redis.RedisService;
import com.student_management.demo.service.summary.SummaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.student_management.demo.common.error.ErrorCodeConstants.*;
import static com.student_management.demo.utils.exception.ServiceExceptionUtil.exception;

@Service("studentService")
@Slf4j
public class StudentServiceImpl implements StudentService{
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private YearMapper yearMapper;
    @Resource
    private MajorMapper majorMapper;
    @Resource
    private ClassMapper classMapper;
    @Resource
    private SummaryService summaryService;
    @Resource
    private RedisService redisService;

    /**
     * 导入学生信息
     * @param importStudent     导入学生信息列表
     * @return
     */
    @Override
    public StudentImportRespVO importStudentList(List<StudentImportExcelReqVO> importStudent, String num) {
        StudentImportRespVO respVO = StudentImportRespVO.builder().createsStudentNames(new ArrayList<>())
                .updateStudentNames(new ArrayList<>()).failureStudentNames(new LinkedHashMap<>()).build();
        //列表为空
        if (CollUtil.isEmpty(importStudent)) {
            throw exception(USER_IMPORT_LIST_IS_EMPTY);
        }
        Long operateId = Long.parseLong(redisService.getValue("user_id_" + num));

        //将yearname，majorname，classname转为id
        List<StudentDO> studentDOList= transferNameToId(importStudent);
        //对每一个表项检查
        studentDOList.forEach(student -> {
            // 判断是否在学生信息表stu_info中，在进行插入
            StudentDO existStu = studentMapper.selectStudentByNum(student.getNum());
            if (existStu == null) {
                // 如果学生表中不存在，在学生表中插入记录
                // 使用当前操作者的用户id作为创建和更新id
                student.setCreateUserId(operateId);
                student.setUpdateUserId(operateId);

                studentMapper.insert(student);
                respVO.getCreatesStudentNames().add(student.getName());

            } else {

                // 如果存在，更新学生表表中的记录;
                // 保留 id
                student.setId(existStu.getId());

                // 使用 当前操作者id 作为 更新者id, 当前时间 为 更新时间
                student.setUpdateUserId(operateId);
                student.setUpdateTime(new Timestamp(System.currentTimeMillis()));

                studentMapper.updateById(student);
                respVO.getUpdateStudentNames().add(student.getName());

            }
        });
        return respVO;
    }

    /**
     * 将sex，year，class，major转为id
     * @param importStudent
     * @return
     */
    private List<StudentDO> transferNameToId(List<StudentImportExcelReqVO> importStudent) {
        List<StudentDO> s_list = new ArrayList<>();
        for (StudentImportExcelReqVO s_excel: importStudent) {
            StudentDO s = new StudentDO();
            s.setName(s_excel.getName());
            s.setNum(s_excel.getNum());
            s.setEmail(s_excel.getEmail());
            s.setSex(s_excel.getSex().equals("男")? 1:0);
            s.setClassId(classMapper.selectIdByName(s_excel.getClassName()));
            s.setMajorId(majorMapper.selectIdByName(s_excel.getMajorName()));
            s.setYearId(yearMapper.selectIdByName(s_excel.getYearName()));
            s_list.add(s);
        }
        return s_list;
    }

    /**
     * 查找所有学生信息
     * @return
     */
    @Override
    public List<StudentBasicDO> selectALLList() {
        List<StudentDO> studentlist = studentMapper.selectStudent();
        List<StudentBasicDO> basicDOs = new ArrayList<>();
        // 将学年，学苑，专业转换为字符串
        for (StudentDO s: studentlist) {
            StudentBasicDO sbdo = StudentConvert.INSTANCE.convert(s);
            Long id = s.getId();
            //year
            sbdo.setYearName(studentMapper.selectYearNameById(id));
            //class
            sbdo.setClassName(studentMapper.selectClassNameById(id));
            //major
            sbdo.setMajorName(studentMapper.selectMajorNameById(id));
            basicDOs.add(sbdo);
        }
        return basicDOs;
    }

}
