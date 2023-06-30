package com.student_management.demo.controller.grade;
import com.student_management.demo.common.CommonResult;
import com.student_management.demo.controller.grade.vo.*;
import com.student_management.demo.service.grade.GradeService;
import com.student_management.demo.service.summary.SummaryService;
import com.student_management.demo.utils.excel.ExcelUtils;
import com.student_management.demo.utils.token.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("/api/grade/")
@Api(tags = "GPA相关接口")
public class GradeController {
    @Resource
    private GradeService service;

    @Resource
    private SummaryService summaryService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 上传GPA excel表格
     * @param file
     * @throws IOException
     */
    @PostMapping("/import")
    @PreAuthorize("hasAuthority('/api/grade/import')")
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
    @PreAuthorize("hasAuthority('/api/grade/selectListAll')")
    public CommonResult<GradeSelectListRespVO> selectListAll() {
        return CommonResult.success(service.selectAllStudents());
    }

    @PostMapping("/update-score")
    @ApiOperation("根据学号更新评分接口")
    @PreAuthorize("hasAuthority('/api/grade/update-score')")
    public CommonResult<String> updateScoreByStuNum(
            @RequestBody GradeScoreReqVO reqVO
    ) {
        try {
            GradeScoreReqVO gradeScoreVO = new GradeScoreReqVO();

            String stuNum = reqVO.getStuNum();
            if (service.isDeleted(stuNum)) {
               return CommonResult.error(404, "该学生的信息已删除，请联系学工添加该学生的信息");
            }

            gradeScoreVO.setStuNum(stuNum);
            gradeScoreVO.setScore(reqVO.getScore());
            boolean success = service.updateResult(gradeScoreVO);

            //if (success) {
                //Integer.parseInt(score);
            /*SummaryDO summary = new SummaryDO();
            summary.setStuNum(reqVO.getStuNum());
            summary.setGpa(reqVO.getScore());
            boolean success = summaryService.updateGpaByStuNum(summary);*/
            if (success) {
                return CommonResult.success("评分更新成功");
            } else {
                return CommonResult.error(404, "在学生成绩表中找不到指定的记录，请联系学工添加该学生的信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.error(500, "评分更新失败");
        }
    }

    @GetMapping("/get-grade-info")
    @PreAuthorize("hasAuthority('/api/grade/get-grade-info')")
    @ApiOperation("根据token获取学生学号，之后获取学生GPA信息")
    public CommonResult<GradeRespVO> getInfoByStuNum(@RequestHeader("Authorization") String authHeader) {
        //return CommonResult.success(userBasicService.getBasicInfo(username));
        try {
            String stuNum = jwtTokenUtil.getUsernameFromToken(authHeader);//id,且学生和老师id不会重复
            //System.out.println("/get-grade-info:stuNum:" + stuNum);
            GradeRespVO info = service.getInfoByStuNum(stuNum);
            //System.out.println(info);
            return CommonResult.success(info);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.error(500, "获取学生信息失败");
        }
    }
}