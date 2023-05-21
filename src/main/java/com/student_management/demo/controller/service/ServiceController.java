package com.student_management.demo.controller.service;

import com.student_management.demo.CommonResult;
import com.student_management.demo.controller.service.vo.ServiceRespVO;
import com.student_management.demo.controller.service.vo.ServiceImportReqVO;
import com.student_management.demo.controller.service.vo.ServiceImportRespVO;
import com.student_management.demo.convert.service.ServiceConvert;
import com.student_management.demo.mapper.dataobject.service.ServiceDO;
import com.student_management.demo.service.service.ServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
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

    @GetMapping("/list")
    @ApiOperation("获得骨干服务岗位情况列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    public CommonResult<List<ServiceRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<ServiceDO> list = service.getList(ids);
        return CommonResult.success(ServiceConvert.INSTANCE.convertList(list));
    }


}
