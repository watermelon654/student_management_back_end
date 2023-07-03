package com.student_management.demo.controller.summary;

import com.student_management.demo.common.CommonResult;
import com.student_management.demo.controller.summary.vo.SummaryDeleteReqVO;
import com.student_management.demo.controller.summary.vo.SummarySelectListRespVO;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private SummaryService service;

    @ApiOperation("成绩表上传接口")
    @PostMapping("/import")
    @PreAuthorize("hasAuthority('/api/summary/import')")
    public CommonResult<SummaryImportRespVO> importSummarySheet(@RequestBody List<SummaryImportReqVO> userList) {
        SummaryImportRespVO respVO = service.importRecord(userList);
        return CommonResult.success(respVO);

    }
    @ApiOperation("根据状态挑选学生")
    @PostMapping("/selectList")
    @PreAuthorize("hasAuthority('/api/summary/selectList')")
    public CommonResult<SummarySelectListRespVO> selectList(@RequestParam Integer flag) {
            System.out.println("调用selectList");
            return flag == 0 ?
                    CommonResult.success(service.selectListByStatus(false)) :
                    CommonResult.success(service.selectListByStatus(true));
    }

    @ApiOperation("选取所有学生summary数据")
    @GetMapping("/selectAllList")
    @PreAuthorize("hasAuthority('/api/summary/selectAllList')")
    public CommonResult<SummarySelectListRespVO> selectAllList() {
        return CommonResult.success(service.selectAllList());
    }

    @ApiOperation("删除指定学生的成绩记录")
    @PostMapping("/deleteSummary")
    @PreAuthorize("hasAuthority('/api/summary/deleteSummary')")
    public CommonResult<?> deleteList(@RequestBody List<SummaryDeleteReqVO> reqVOs) {
        return service.deleteList(reqVOs);
    }


}
