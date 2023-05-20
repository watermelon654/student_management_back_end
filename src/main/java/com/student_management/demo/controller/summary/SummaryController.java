package com.student_management.demo.controller.summary;

import com.student_management.demo.CommonResult;
import com.student_management.demo.controller.summary.vo.SummaryImportReqVO;
import com.student_management.demo.controller.summary.vo.SummaryImportRespVO;
import com.student_management.demo.controller.summary.vo.*;
import com.student_management.demo.service.summary.SummaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/summary/")
@Api(tags = "summary表相关接口")
public class SummaryController {
    @Resource
    private SummaryService service;

    @ApiOperation("成绩表上传接口")
    @PostMapping("/import")
    // CommonResult<SummaryImportRespVO>
    public CommonResult<SummaryImportRespVO> importSummarySheet(@RequestBody List<SummaryImportReqVO> userList) {
//        console.log(reqVO)
        System.out.println(userList);
//        List<SummaryImportReqVO> userList = ExcelUtils.read(file,GradeImportExcelVO.class);
//        GradeImportRespVO respVO = service.importGradeList(userList);

        SummaryImportRespVO respVO = service.importRecord(userList);
        return CommonResult.success(respVO);

    }


//    @PostMapping("/create")
//    @ApiOperation("创建学生评分")
//    public CommonResult<Long> create(@Valid @RequestBody SummaryCreateReqVO createReqVO) {
//        return CommonResult.success(service.create(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @ApiOperation("更新学生评分")
//    public CommonResult<Boolean> update(@Valid @RequestBody SummaryUpdateReqVO updateReqVO) {
//        service.update(updateReqVO);
//        return CommonResult.success(true);
//    }

//    @DeleteMapping("/delete")
//    @Operation(summary = "删除学生评分")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('summary::delete')")
//    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
//        Service.delete(id);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得学生评分")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('summary::query')")
//    public CommonResult<RespVO> get(@RequestParam("id") Long id) {
//        DO  = Service.get(id);
//        return success(Convert.INSTANCE.convert());
//    }
//
//    @GetMapping("/list")
//    @Operation(summary = "获得学生评分列表")
//    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
//    @PreAuthorize("@ss.hasPermission('summary::query')")
//    public CommonResult<List<RespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
//        List<DO> list = Service.getList(ids);
//        return success(Convert.INSTANCE.convertList(list));
//    }
//
//    @GetMapping("/page")
//    @Operation(summary = "获得学生评分分页")
//    @PreAuthorize("@ss.hasPermission('summary::query')")
//    public CommonResult<PageResult<RespVO>> getPage(@Valid PageReqVO pageVO) {
//        PageResult<DO> pageResult = Service.getPage(pageVO);
//        return success(Convert.INSTANCE.convertPage(pageResult));
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出学生评分 Excel")
//    @PreAuthorize("@ss.hasPermission('summary::export')")
//    @OperateLog(type = EXPORT)
//    public void exportExcel(@Valid ExportReqVO exportReqVO,
//                            HttpServletResponse response) throws IOException {
//        List<DO> list = Service.getList(exportReqVO);
//        // 导出 Excel
//        List<ExcelVO> datas = Convert.INSTANCE.convertList02(list);
//        ExcelUtils.write(response, "学生评分.xls", "数据", ExcelVO.class, datas);
//    }

}
