package com.student_management.demo.controller.practice;

import com.student_management.demo.CommonResult;
import com.student_management.demo.controller.practice.vo.PracticeImportReqVO;
import com.student_management.demo.controller.practice.vo.PracticeImportRespVO;
import com.student_management.demo.service.practice.PracticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/practice/")
@Api(tags = "社会实践表相关接口")
public class PracticeController {

    @Resource
    private PracticeService service;

    @ApiOperation("社会实践表上传接口")
    @PostMapping("/import")
    // CommonResult<PracticeImportRespVO>
    public CommonResult<PracticeImportRespVO> importPracticeSheet(@RequestBody List<PracticeImportReqVO> userList) {
//        console.log(reqVO)
        System.out.println(userList);
//        List<PracticeImportReqVO> userList = ExcelUtils.read(file,GradeImportExcelVO.class);
//        GradeImportRespVO respVO = service.importGradeList(userList);

        PracticeImportRespVO respVO = service.importRecord(userList);
        return CommonResult.success(respVO);
    }

}
