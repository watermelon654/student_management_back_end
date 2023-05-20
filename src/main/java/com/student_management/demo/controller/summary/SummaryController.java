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
import com.student_management.demo.controller.summary.vo.SummaryImportReqVO;
import com.student_management.demo.controller.summary.vo.SummaryImportRespVO;
import com.student_management.demo.controller.summary.vo.*;
import com.student_management.demo.service.summary.SummaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/summary/")
@Api(tags = "summary表相关接口")
public class SummaryController {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private SummaryMapper summaryMapper;

    @Resource
    private StudentMapper1 studentMapper1;

    @Resource
    private SummaryService service;

    @ApiOperation("成绩表上传接口")
    @PostMapping("/import")
    // CommonResult<SummaryImportRespVO>
    public CommonResult<SummaryImportRespVO> importSummarySheet(@RequestBody List<SummaryImportReqVO> userList) {
//        console.log(reqVO)
        System.out.println(userList);
//        List<SummaryImportReqVO> userList = ExcelUtils.read(file,GradeImportExcelVO.class);
//        GradeImportRespVO respVO = service.importGradeList(userList);

        SummaryImportRespVO respVO = service.importRecord(userList);
        return CommonResult.success(respVO);

    }

//    @RequestMapping("/selectbystatus")
//    public CommonResult<List<SummaryVO>> selectall() throws IOException {
//
//
//        List<Long> longs = studentMapper.selectStudentbystatus();
//
//        List<SummaryVO> summaryVOS = summaryMapper.selectSummary(longs);
//
//
//        return CommonResult.success(summaryVOS);
//
//    }
//
//
//
//    @RequestMapping("/selectbystatus1")
//    public CommonResult<List<SummaryVO>> selectall1() throws IOException {
//
//
//        List<Long> longs = studentMapper.selectStudentbystatus1();
//
//        List<SummaryVO> summaryVOS = summaryMapper.selectSummary(longs);
//
//
//        return CommonResult.success(summaryVOS);
//
//    }
//
//
//    @RequestMapping("/update")
//    public CommonResult<List<SummaryVO>> update(@RequestBody SummaryVO summary) throws IOException {
//
//        summaryMapper.update(summary);
//
//
//        StudentDO studentDO = studentMapper.selectStudentByNum(summary.getStuNum());
//
//        studentDO.setStatus(0);
//
//        int a = studentMapper.updateStu(studentDO);
//
//
//
//        return CommonResult.success(null);
//
//    }



}
