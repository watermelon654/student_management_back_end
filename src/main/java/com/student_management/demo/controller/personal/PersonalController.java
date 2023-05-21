package com.student_management.demo.controller.personal;

import com.student_management.demo.CommonResult;
import com.student_management.demo.controller.personal.vo.PersonalImportRespVO;
import com.student_management.demo.controller.personal.vo.PersonalImportReqVO;
import com.student_management.demo.controller.personal.vo.PersonalImportRespVO;
import com.student_management.demo.controller.personal.vo.PersonalRespVO;
import com.student_management.demo.convert.personal.PersonalConvert;
import com.student_management.demo.mapper.dataobject.personal.PersonalDO;
import com.student_management.demo.service.personal.PersonalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/personal/")
@Api(tags = "个人学年总结表相关接口")
public class PersonalController {
    @Resource
    private PersonalService service;

    @ApiOperation("个人学年总结表上传接口")
    @PostMapping("/import")
    public CommonResult<PersonalImportRespVO> importPersonalSheet(@RequestBody List<PersonalImportReqVO> userList) {
//        System.out.println(userList);

        PersonalImportRespVO respVO = service.importRecord(userList);
        return CommonResult.success(respVO);
    }

    @GetMapping("/list")
    @ApiOperation("获得个人学年总结列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    public CommonResult<List<PersonalRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<PersonalDO> list = service.getList(ids);
        return CommonResult.success(PersonalConvert.INSTANCE.convertList(list));
    }

}
