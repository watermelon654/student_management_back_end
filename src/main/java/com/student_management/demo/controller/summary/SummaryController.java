package com.student_management.demo.controller.summary;

import com.student_management.demo.CommonResult;
import com.student_management.demo.controller.summary.vo.SummaryVO;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import com.student_management.demo.mapper.mysql.student.StudentMapper1;
import com.student_management.demo.mapper.mysql.summary.SummaryMapper;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/summary/")
@Api(tags = "EasyExcel")
public class SummaryController {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private SummaryMapper summaryMapper;

    @Resource
    private StudentMapper1 studentMapper1;


    @RequestMapping("/selectbystatus")
    public CommonResult<List<SummaryVO>> selectall() throws IOException {


        List<Long> longs = studentMapper.selectStudentbystatus();

        List<SummaryVO> summaryVOS = summaryMapper.selectSummary(longs);


        return CommonResult.success(summaryVOS);

    }



    @RequestMapping("/selectbystatus1")
    public CommonResult<List<SummaryVO>> selectall1() throws IOException {


        List<Long> longs = studentMapper.selectStudentbystatus1();

        List<SummaryVO> summaryVOS = summaryMapper.selectSummary(longs);


        return CommonResult.success(summaryVOS);

    }


    @RequestMapping("/update")
    public CommonResult<List<SummaryVO>> update(@RequestBody SummaryVO summary) throws IOException {

        summaryMapper.update(summary);


        StudentDO studentDO = studentMapper.selectStudentByNum(summary.getStuNum());

        studentDO.setStatus(0);

            int a = studentMapper.updateStu(studentDO);



        return CommonResult.success(null);

    }



}
