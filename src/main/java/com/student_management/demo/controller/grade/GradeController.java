package com.student_management.demo.controller.grade;
import com.student_management.demo.common.CommonResult;
import com.student_management.demo.controller.grade.vo.Judge.GradeImportExcelVO;
import com.student_management.demo.controller.grade.vo.Judge.GradeImportRespVO;
import com.student_management.demo.controller.grade.vo.Judge.GradeScoreReqVO;
import com.student_management.demo.controller.grade.vo.Judge.GradeSelectListRespVO;
import com.student_management.demo.controller.grade.vo.Student.StudentGradeRespVO;
import com.student_management.demo.service.grade.GradeService;
import com.student_management.demo.service.redis.RedisService;
import com.student_management.demo.service.summary.SummaryService;
import com.student_management.demo.utils.excel.ExcelUtils;
import com.student_management.demo.utils.token.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("/api/grade/")
@Api(tags = "GPA相关接口")
public class GradeController {
    @Resource
    private GradeService service;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    //--------------------------------------
    //评委端

    /**
     * 上传GPA excel表格
     * @param file
     * @throws IOException
     */
    @PostMapping("/import")
    @PreAuthorize("hasAuthority('/api/grade/import')")
    public CommonResult<GradeImportRespVO> importGradeExcel(
            @RequestPart(value = "file") MultipartFile file,
            HttpServletRequest request) throws IOException {
        // 从http请求获取token，然后获得评委职工号
        String token = request.getHeader("Authorization");
        String judgeNum = jwtTokenUtil.getUsernameFromToken(token);

        List<GradeImportExcelVO> userList = ExcelUtils.read(file, GradeImportExcelVO.class);
        GradeImportRespVO respVO = service.importGradeList(userList, judgeNum);

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
            @RequestBody GradeScoreReqVO reqVO,
            HttpServletRequest request
    ) {
        try {
            if (service.isDeleted(reqVO.getStuNum())) {
               return CommonResult.error(404, "该学生的信息已不在成绩表中");
            }
            if (service.isDeletedInStuinfo(reqVO.getStuNum())) {
                return CommonResult.error(404, "该学生的信息已不在学工表中，无法进行操作");
            }

            // 从http请求获取token，然后获得评委职工号
            String token = request.getHeader("Authorization");
            String judgeNum = jwtTokenUtil.getUsernameFromToken(token);
            reqVO.setJudgeNum(judgeNum);

            boolean success = service.updateResult(reqVO);

            if (success) {
                return CommonResult.success("评分更新成功");
            } else {
                return CommonResult.error(404, "评分更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.error(500, "评分更新失败");
        }
    }

    @PostMapping("/deleteGrade")
    @ApiOperation("根据学号删除信息")
    @PreAuthorize("hasAuthority('/api/grade/deleteGrade')")
    public CommonResult<String> deleteByStuNum(
            @RequestBody String stuNumData,
            HttpServletRequest request
    ) {
        try {
            String stuNum = stuNumData.replace("{\"stuNumData\":\"", "").replaceAll("\"}", "");

            if (service.isDeleted(stuNum)) {
                return CommonResult.error(404, "该学生的信息已不在成绩表中");
            }

            // 从http请求获取token，然后获得评委职工号
            String token = request.getHeader("Authorization");
            String judgeNum = jwtTokenUtil.getUsernameFromToken(token);

            boolean success = service.showDeleteResult(judgeNum, stuNum);
            if (success) {
                return CommonResult.success("删除成功");
            } else {
                return CommonResult.error(404, "删除学生信息失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.error(500, "删除学生信息失败");
        }
    }

    //--------------------------------------
    //学生端

    @GetMapping("/get-grade-info")
    @PreAuthorize("hasAuthority('/api/grade/get-grade-info')")
    @ApiOperation("根据token获取学生学号，之后获取学生GPA信息")
    public CommonResult<StudentGradeRespVO> getInfoByStuNum(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String stuNum = jwtTokenUtil.getUsernameFromToken(token);
            StudentGradeRespVO info = service.getInfoByStuNum(stuNum);
            return CommonResult.success(info);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.error(500, "获取学生信息失败");
        }
    }
}