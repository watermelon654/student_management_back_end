package com.student_management.demo.controller.volunteer;

import com.student_management.demo.common.CommonResult;
import com.student_management.demo.controller.grade.vo.GradeRespVO;
import com.student_management.demo.controller.volunteer.vo.VolunteerImportExcelVO;
import com.student_management.demo.controller.volunteer.vo.VolunteerImportRespVO;
import com.student_management.demo.controller.volunteer.vo.VolunteerRespVO;
import com.student_management.demo.controller.volunteer.vo.VolunteerSelectListRespVO;
import com.student_management.demo.mapper.dataobject.summary.SummaryDO;
import com.student_management.demo.mapper.dataobject.volunteer.VolunteerDO;
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
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/volunteer/")
@Api(tags = "志愿服务时长相关接口")
public class VolunteerController {
    @Resource
    private VolunteerService service;

    @Resource
    private SummaryService summaryService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 上传志愿服务 excel表格
     * @param file
     * @throws IOException
     */
    @PostMapping("/import")
    @ApiOperation("志愿服务时长上传接口")
    public CommonResult<VolunteerImportRespVO> importUserExcel(@RequestPart(value = "file") MultipartFile file) throws IOException {
        List<VolunteerImportExcelVO> userList = ExcelUtils.read(file,VolunteerImportExcelVO.class);
        VolunteerImportRespVO respVO = service.importVolunteerList(userList);
        // 检查上传文件是否为空文件
        if (respVO.isEmpty())
            return CommonResult.error(500, "文件内容为空！");
        return CommonResult.success(respVO);
    }

    @ApiOperation("选择全部学生")
    @PostMapping("/selectListAll")
    public CommonResult<VolunteerSelectListRespVO> selectListAll() {
        return CommonResult.success(service.selectAllStudents());
    }

    @PostMapping("/{stuNum}/update-score")
    @ApiOperation("根据学号更新评分接口")
    public CommonResult<String> updateScoreByStuNum(
            @PathVariable("stuNum") String stuNum,
            @RequestParam("score") Integer score
    ) {
        try {
            VolunteerDO volunteer = new VolunteerDO();
            volunteer.setStuNum(stuNum);
            volunteer.setScore(score);
            boolean success = service.updateResult(volunteer);

            if (success) {
                SummaryDO summary = new SummaryDO();
                summary.setStuNum(stuNum);
                summary.setVol(score);
                summaryService.updateVolByStuNum(summary);
                return CommonResult.success("评分更新成功");
            } else {
                return CommonResult.error(404, "找不到指定的记录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.error(500, "评分更新失败");
        }
    }

    @GetMapping("/get-volunteer-info")
    @PreAuthorize("hasAuthority('/user/profile/get')")
    @ApiOperation("根据token获取学生学号，之后获取学生志愿服务时长信息")
    public CommonResult<VolunteerRespVO> getInfoByStuNum(@RequestHeader("Authorization") String authHeader) {
        try {
            String stuNum = jwtTokenUtil.getUsernameFromToken(authHeader);//id,且学生和老师id不会重复
            System.out.println("/get-volunteer-info:stuNum:" + stuNum);
            VolunteerRespVO info = service.getInfoByStuNum(stuNum);
            System.out.println(info);
            return CommonResult.success(info);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.error(500, "获取学生信息失败");
        }
    }
}