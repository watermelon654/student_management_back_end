package com.student_management.demo.controller.summary;

import com.student_management.demo.CommonResult;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import com.student_management.demo.mapper.mysql.student.StudentMapper1;
import com.student_management.demo.mapper.mysql.summary.SummaryMapper;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.student_management.demo.controller.summary.vo.SummaryImportReqVO;
import com.student_management.demo.controller.summary.vo.SummaryImportRespVO;
import com.student_management.demo.service.summary.SummaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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




}
