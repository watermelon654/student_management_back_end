package com.student_management.demo.controller.personal;

import com.student_management.demo.CommonResult;
import com.student_management.demo.controller.personal.vo.PersonalImportRespVO;
import com.student_management.demo.controller.personal.vo.PersonalImportReqVO;
import com.student_management.demo.controller.personal.vo.PersonalImportRespVO;
import com.student_management.demo.service.personal.PersonalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

}
