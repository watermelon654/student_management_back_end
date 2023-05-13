package com.student_management.demo.controller.grade;
import com.student_management.demo.CommonResult;
import com.student_management.demo.controller.grade.vo.GradeImportExcelVO;
import com.student_management.demo.controller.grade.vo.GradeImportRespVO;
import com.student_management.demo.service.grade.GradeService;
import com.student_management.demo.utils.excel.ExcelUtils;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/grade/")
public class GradeController {
    GradeService service;

    /**
     * 上传GPA excel表格
     * @param file
     * @throws IOException
     */
    @PostMapping("/upload")
    @Operation(summary = "导入GPA")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
    })
    @PreAuthorize("@ss.hasPermission('system:grade:upload')")
    public CommonResult<GradeImportRespVO> importCustomerDaily(@RequestParam MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<GradeImportExcelVO> gradeImports = ExcelUtils.read(file, GradeImportExcelVO.class);
        GradeImportRespVO respVO = service.importGradeList(gradeImports);
        if (respVO.isEmpty())
            return CommonResult.error(500, "文件内容为空！");
        return CommonResult.success(respVO);
    }

}
