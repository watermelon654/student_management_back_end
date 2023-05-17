package com.student_management.demo.controller.service;

import com.student_management.demo.CommonResult;
import com.student_management.demo.controller.service.vo.ServiceImportReqVO;
import com.student_management.demo.controller.service.vo.ServiceImportRespVO;
import com.student_management.demo.service.service.ServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/service/")
@Api(tags = "骨干服务表相关接口")
public class ServiceController {

    @Resource
    private ServiceService service;

    @ApiOperation("骨干服务表上传接口")
    @PostMapping("/import")
    // CommonResult<ServiceImportRespVO>
    public CommonResult<ServiceImportRespVO> importServiceSheet(@RequestBody List<ServiceImportReqVO> userList) {
//        console.log(reqVO)
        System.out.println(userList);
//        List<ServiceImportReqVO> userList = ExcelUtils.read(file,GradeImportExcelVO.class);
//        GradeImportRespVO respVO = service.importGradeList(userList);

        ServiceImportRespVO respVO = service.importRecord(userList);
        return CommonResult.success(respVO);
    }

}
