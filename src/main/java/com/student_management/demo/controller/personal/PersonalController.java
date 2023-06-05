package com.student_management.demo.controller.personal;

import com.student_management.demo.common.CommonResult;
import com.student_management.demo.controller.personal.vo.PersonalImportRespVO;
import com.student_management.demo.controller.personal.vo.PersonalImportReqVO;
import com.student_management.demo.controller.personal.vo.PersonalRespVO;
import com.student_management.demo.controller.science.vo.ScienceImportReqVO;
import com.student_management.demo.controller.science.vo.ScienceRespVO;
import com.student_management.demo.convert.personal.PersonalConvert;
import com.student_management.demo.convert.science.ScienceConvert;
import com.student_management.demo.mapper.dataobject.personal.PersonalDO;
import com.student_management.demo.mapper.dataobject.science.ScienceDO;
import com.student_management.demo.service.personal.PersonalService;
import com.student_management.demo.service.user.UserBasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/personal/")
@Api(tags = "个人学年总结表相关接口")
public class PersonalController {
    @Resource
    private PersonalService service;
    @Resource
    private UserBasicService userBasicService;
    @ApiOperation("个人学年总结表上传接口")
    @PostMapping("/import")
    public CommonResult<PersonalImportRespVO> importPersonalSheet(@RequestHeader("Authorization") String authHeader,@RequestBody List<PersonalImportReqVO> userList) {
        String token = authHeader.substring(7);
        Map<String,String> map = userBasicService.getCurrentUserInfo(token);

        for (PersonalImportReqVO user : userList) {
            user.setStuId(Long.parseLong(map.get("id")));
            user.setStuName(map.get("name"));
            user.setStuNum(map.get("num"));
            user.setCreateUserId(Long.parseLong(map.get("id")));
            user.setUpdateUserId(Long.parseLong(map.get("id")));
        }
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

    @GetMapping("/getAllList")
    @ApiOperation("获得个人学年总结所有列表")
    public CommonResult<List<PersonalRespVO>> getAllList() {
        List<PersonalDO> list = service.getAllList();
        return CommonResult.success(PersonalConvert.INSTANCE.convertList(list));
    }

}
