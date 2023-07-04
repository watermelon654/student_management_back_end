package com.student_management.demo.controller.volunteer;

import com.student_management.demo.common.CommonResult;
import com.student_management.demo.controller.volunteer.vo.Judge.VolunteerImportExcelVO;
import com.student_management.demo.controller.volunteer.vo.Judge.VolunteerImportRespVO;
import com.student_management.demo.controller.volunteer.vo.Judge.VolunteerScoreReqVO;
import com.student_management.demo.controller.volunteer.vo.Judge.VolunteerSelectListRespVO;
import com.student_management.demo.controller.volunteer.vo.Student.StudentVolunteerRespVO;
import com.student_management.demo.service.summary.SummaryService;
import com.student_management.demo.service.volunteer.VolunteerService;
import com.student_management.demo.utils.excel.ExcelUtils;
import com.student_management.demo.utils.token.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/volunteer/")
@Api(tags = "志愿服务时长相关接口")
public class VolunteerController {
    @Resource
    private VolunteerService service;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    //--------------------------------------
    //评委端

    /**
     * 上传志愿服务 excel表格
     * @param file
     * @throws IOException
     */
    @PostMapping("/import")
    @ApiOperation("志愿服务时长上传接口")
    @PreAuthorize("hasAuthority('/api/volunteer/import')")
    public CommonResult<VolunteerImportRespVO> importUserExcel(@RequestPart(value = "file") MultipartFile file, HttpServletRequest request) throws IOException {
        // 从http请求获取token，然后获得评委职工号
        String token = request.getHeader("Authorization");
        String judgeNum = jwtTokenUtil.getUsernameFromToken(token);

        List<VolunteerImportExcelVO> userList = ExcelUtils.read(file, VolunteerImportExcelVO.class);
        VolunteerImportRespVO respVO = service.importVolunteerList(userList, judgeNum);
        // 检查上传文件是否为空文件
        if (respVO.isEmpty())
            return CommonResult.error(500, "文件内容为空！");
        return CommonResult.success(respVO);
    }

    @ApiOperation("选择全部学生")
    @PostMapping("/selectListAll")
    @PreAuthorize("hasAuthority('/api/volunteer/selectListAll')")
    public CommonResult<VolunteerSelectListRespVO> selectListAll() {
        return CommonResult.success(service.selectAllStudents());
    }

    @PostMapping("/update-score")
    @ApiOperation("根据学号更新评分接口")
    @PreAuthorize("hasAuthority('/api/volunteer/update-score')")
    public CommonResult<String> updateScoreByStuNum(
            @RequestBody VolunteerScoreReqVO reqVO,
            HttpServletRequest request
    ) {
        try {
            String stuNum = reqVO.getStuNum();
            if (service.isDeleted(stuNum)) {
                return CommonResult.error(404, "该学生的信息已不在成绩表中");
            }
            if (service.isDeletedInStuinfo(stuNum)) {
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

    @PostMapping("/deleteVolunteer")
    @ApiOperation("根据学号删除信息")
    @PreAuthorize("hasAuthority('/api/volunteer/deleteVolunteer')")
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

    @GetMapping("/get-volunteer-info")
    @PreAuthorize("hasAuthority('/api/volunteer/get-volunteer-info')")
    @ApiOperation("根据token获取学生学号，之后获取学生志愿服务时长信息")
    public CommonResult<StudentVolunteerRespVO> getInfoByStuNum(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String stuNum = jwtTokenUtil.getUsernameFromToken(token);
            StudentVolunteerRespVO info = service.getInfoByStuNum(stuNum);
            return CommonResult.success(info);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.error(500, "获取学生信息失败");
        }
    }
}