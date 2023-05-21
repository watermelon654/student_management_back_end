package com.student_management.demo.controller.practice;

import com.student_management.demo.CommonResult;
import com.student_management.demo.controller.practice.vo.PracticeImportReqVO;
import com.student_management.demo.controller.practice.vo.PracticeImportRespVO;
import com.student_management.demo.controller.practice.vo.PracticeRespVO;
import com.student_management.demo.convert.practice.PracticeConvert;
import com.student_management.demo.mapper.dataobject.practice.PracticeDO;
import com.student_management.demo.service.practice.PracticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
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

    @GetMapping("/list")
    @ApiOperation("获得社会实践情况列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    public CommonResult<List<PracticeRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<PracticeDO> list = service.getList(ids);
        return CommonResult.success(PracticeConvert.INSTANCE.convertList(list));
    }


}
