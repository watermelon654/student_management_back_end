package com.student_management.demo.controller.science;

import com.student_management.demo.CommonResult;
import com.student_management.demo.controller.science.vo.ScienceImportReqVO;
import com.student_management.demo.controller.science.vo.ScienceImportRespVO;
import com.student_management.demo.service.science.ScienceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/science/")
@Api(tags = "科研表相关接口")
public class ScienceController {

    @Resource
    private ScienceService service;

    @ApiOperation("科研表上传接口")
    @PostMapping("/import")
    // CommonResult<ScienceImportRespVO>
    public CommonResult<ScienceImportRespVO> importScienceSheet(@RequestBody List<ScienceImportReqVO> userList) {
//        console.log(reqVO)
        System.out.println(userList);
//        List<ScienceImportReqVO> userList = ExcelUtils.read(file,GradeImportExcelVO.class);
//        GradeImportRespVO respVO = service.importGradeList(userList);

        ScienceImportRespVO respVO = service.importRecord(userList);
        return CommonResult.success(respVO);
    }

}
