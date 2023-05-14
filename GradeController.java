package com.student_management.demo.controller.grade;
import com.alibaba.excel.EasyExcel;
import com.student_management.demo.CommonResult;
import com.student_management.demo.controller.grade.vo.GradeImportExcelVO;
import com.student_management.demo.controller.grade.vo.GradeImportRespVO;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;
import com.student_management.demo.service.grade.GradeService;
import com.student_management.demo.utils.excel.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/grade/")
@Api(tags = "EasyExcel")
@CrossOrigin(origins = "*")
public class GradeController {
    @Resource
    private GradeService service;

    /**
     * 上传GPA excel表格
     * @param file
     * @throws IOException
     */
        @PostMapping("/import")
        public void importUserExcel(@RequestPart(value = "file") MultipartFile file) {
            try {
                System.out.println(file.getSize());
                List<GradeImportExcelVO> userList = EasyExcel.read(file.getInputStream())
                        .head(GradeImportExcelVO.class)
                        .sheet()
                        .doReadSync();
                System.out.println(userList);
//                GradeImportRespVO respVO = service.importGradeList(userList);
                return;
            } catch (IOException e) {
                return;
            }
        }
//    public void uploadFile(@RequestBody MultipartFile file ){
//        System.out.println("file:" + file);
//    }
//    @Operation(summary = "导入GPA")
//    @Parameters({
//    @Parameter(name = "file", description = "Excel 文件", required = true)
//    })
    @PostMapping("/upload")
//    @PreAuthorize("@ss.hasPermission('system:grade:upload')")
    public CommonResult<GradeImportRespVO> importCustomerDaily(@RequestParam MultipartFile file) throws IOException {
//        InputStream inputStream = file.getInputStream();
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        System.out.println("1111111");

        List<GradeImportExcelVO> gradeImports = ExcelUtils.read(file, GradeImportExcelVO.class);
        GradeImportRespVO respVO = service.importGradeList(gradeImports);
//        inputStream.close();
        if (respVO.isEmpty())
            return CommonResult.error(500, "文件内容为空！");
        return CommonResult.success(respVO);
    }

}
