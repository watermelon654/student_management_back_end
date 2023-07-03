package com.student_management.demo.controller.practice;

import com.student_management.demo.common.CommonResult;
import com.student_management.demo.controller.practice.vo.PracticeImportReqVO;
import com.student_management.demo.controller.practice.vo.PracticeImportRespVO;
import com.student_management.demo.controller.practice.vo.PracticeRespVO;
import com.student_management.demo.controller.service.vo.ServiceRespVO;
import com.student_management.demo.convert.practice.PracticeConvert;
import com.student_management.demo.convert.service.ServiceConvert;
import com.student_management.demo.mapper.dataobject.practice.PracticeDO;
import com.student_management.demo.mapper.dataobject.service.ServiceDO;
import com.student_management.demo.service.practice.PracticeService;
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
@RequestMapping("/api/practice/")
@Api(tags = "社会实践表相关接口")
public class PracticeController {

    @Resource
    private PracticeService service;

    @Resource
    private UserBasicService userBasicService;

    @ApiOperation("社会实践表上传接口")
    @PostMapping("/import")
    public CommonResult<PracticeImportRespVO> importScienceSheet(@RequestHeader("Authorization") String authHeader,@RequestBody List<PracticeImportReqVO> userList) {
        String token = authHeader.substring(7);
        Map<String,String> map = userBasicService.getCurrentUserInfo(token);

        for (PracticeImportReqVO user : userList) {
            user.setStuId(Long.parseLong(map.get("id")));
            user.setStuName(map.get("name"));
            user.setStuNum(map.get("num"));
            user.setCreateUserId(Long.parseLong(map.get("id")));
            user.setUpdateUserId(Long.parseLong(map.get("id")));
        }

//        List<ScienceImportReqVO> userList = ExcelUtils.read(file,GradeImportExcelVO.class);
//        GradeImportRespVO respVO = service.importGradeList(userList);

        PracticeImportRespVO respVO = service.importRecord(userList);
        return CommonResult.success(respVO);
    }
    // CommonResult<PracticeImportRespVO>

//    public CommonResult<PracticeImportRespVO> importPracticeSheet(@RequestBody List<PracticeImportReqVO> userList) {
////        console.log(reqVO)
//        System.out.println(userList);
////        List<PracticeImportReqVO> userList = ExcelUtils.read(file,GradeImportExcelVO.class);
////        GradeImportRespVO respVO = service.importGradeList(userList);
//
//        PracticeImportRespVO respVO = service.importRecord(userList);
//        return CommonResult.success(respVO);
//    }

    @GetMapping("/list")
    @ApiOperation("获得社会实践情况列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    public CommonResult<List<PracticeRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<PracticeDO> list = service.getList(ids);
        return CommonResult.success(PracticeConvert.INSTANCE.convertList(list));
    }


    @GetMapping("/getAllList")
    @ApiOperation("获得社会实践情况所有列表")
    public CommonResult<List<PracticeRespVO>> getAllList() {
        List<PracticeDO> list = service.getAllList();
        return CommonResult.success(PracticeConvert.INSTANCE.convertList(list));
    }
 @GetMapping("/getMyList")
    @ApiOperation("学生获得自己填写的社会实践情况所有列表")
    @PreAuthorize("hasAuthority('/api/practice/getMyList')")
    public CommonResult<List<PracticeRespVO>> getMyList(String num) {
        System.out.println("num = " + num);
        List<PracticeDO> list = service.getMyList(num);
        return CommonResult.success(PracticeConvert.INSTANCE.convertList(list));
    }

}
