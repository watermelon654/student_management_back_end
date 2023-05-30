package com.student_management.demo.controller.grade;
import com.student_management.demo.common.CommonResult;
import com.student_management.demo.controller.grade.vo.GradeImportExcelVO;
import com.student_management.demo.controller.grade.vo.GradeImportRespVO;
import com.student_management.demo.controller.grade.vo.GradeRespVO;
import com.student_management.demo.controller.grade.vo.GradeSelectListRespVO;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;
import com.student_management.demo.mapper.dataobject.summary.SummaryDO;
import com.student_management.demo.service.grade.GradeService;
import com.student_management.demo.service.summary.SummaryService;
import com.student_management.demo.utils.excel.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("/api/grade/")
@Api(tags = "EasyExcel")
public class GradeController {
    @Resource
    private GradeService service;

    @Resource
    private SummaryService summaryService;

    /**
     * 上传GPA excel表格
     * @param file
     * @throws IOException
     */
    @PostMapping("/import")
    public CommonResult<GradeImportRespVO> importGradeExcel(@RequestPart(value = "file") MultipartFile file) throws IOException {
            List<GradeImportExcelVO> userList = ExcelUtils.read(file,GradeImportExcelVO.class);
            GradeImportRespVO respVO = service.importGradeList(userList);
            // 检查上传文件是否为空文件
            if (respVO.isEmpty())
                return CommonResult.error(500, "文件内容为空！");
            return CommonResult.success(respVO);
    }

    @ApiOperation("选择全部学生")
    @PostMapping("/selectListAll")
    public CommonResult<GradeSelectListRespVO> selectListAll() {
        return CommonResult.success(service.selectAllStudents());
    }

    @PostMapping("/{stuNum}/update-score")
    @ApiOperation("根据学号更新评分接口")
    public CommonResult<String> updateScoreByStuNum(
            @PathVariable("stuNum") String stuNum,
            @RequestParam("score") Integer score
    ) {
        try {
            GradeDO grade = new GradeDO();
            grade.setStuNum(stuNum);
            grade.setScore(score);
            boolean success = service.updateResult(grade);

            if (success) {
                SummaryDO summary = new SummaryDO();
                summary.setStuNum(stuNum);
                summary.setGpa(score);
                summaryService.updateGpaByStuNum(summary);
                return CommonResult.success("评分更新成功");
            } else {
                return CommonResult.error(404, "找不到指定的记录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.error(500, "评分更新失败");
        }
    }

    @PostMapping("/{stuId}/get-grade-info")
    @ApiOperation("根据学生ID获取学生信息接口")
    public CommonResult<GradeRespVO> getInfoByStuId(@PathVariable("stuId") Long stuId) {
        try {
            GradeRespVO info = service.getInfoByStuId(stuId);
            return CommonResult.success(info);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.error(500, "获取学生信息失败");
        }
    }
}