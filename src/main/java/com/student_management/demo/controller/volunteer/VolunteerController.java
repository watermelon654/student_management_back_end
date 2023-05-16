package com.student_management.demo.controller.volunteer;

import com.student_management.demo.CommonResult;
import com.student_management.demo.controller.personal.vo.PersonalImportReqVO;
import com.student_management.demo.controller.personal.vo.PersonalImportRespVO;
import com.student_management.demo.controller.volunteer.vo.VolunteerImportExcelVO;
import com.student_management.demo.controller.volunteer.vo.VolunteerImportRespVO;
import com.student_management.demo.service.volunteer.VolunteerService;
import com.student_management.demo.utils.excel.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/volunteer/")
@Api(tags = "EasyExcel")
public class VolunteerController {
    @Resource
    private VolunteerService service;

    /**
     * 上传志愿服务 excel表格
     * @param file
     * @throws IOException
     */
    @PostMapping("/import")
    public CommonResult<VolunteerImportRespVO> importUserExcel(@RequestPart(value = "file") MultipartFile file) throws IOException {
        List<VolunteerImportExcelVO> userList = ExcelUtils.read(file,VolunteerImportExcelVO.class);
        VolunteerImportRespVO respVO = service.importVolunteerList(userList);
        // 检查上传文件是否为空文件
        if (respVO.isEmpty())
            return CommonResult.error(500, "文件内容为空！");
        return CommonResult.success(respVO);
    }

}