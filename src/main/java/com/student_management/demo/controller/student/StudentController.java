
package com.student_management.demo.controller.student;

import com.student_management.demo.CommonResult;
import com.student_management.demo.controller.student.vo.StudentImportExcelReqVO;
import com.student_management.demo.controller.student.vo.StudentImportRespVO;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.service.student.StudentService;
import com.student_management.demo.utils.excel.ExcelUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/stu/")
@Api(tags = "学工导入学生信息")
public class StudentController {

    @Resource
    private StudentService studentService;


    /**
     * 上传GPA excel表格
     * @param file
     * @throws IOException
     */
    @PostMapping("/import")
    public CommonResult<StudentImportRespVO> importStudentExcel(@RequestPart(value = "file") MultipartFile file) throws IOException {
        List<StudentImportExcelReqVO> studentList = ExcelUtils.read(file, StudentImportExcelReqVO.class);
        StudentImportRespVO respVO = studentService.importStudentList(studentList);
        // 检查上传文件是否为空文件
        if (respVO.isEmpty())
            return CommonResult.error(500, "文件内容为空！");
        return CommonResult.success(respVO);
    }


    /**
     * 查找当前所有学生信息
     * @return
     * @throws IOException
     */
    @RequestMapping("/selectall")
    public CommonResult<List<StudentDO>> selectall() {
        return CommonResult.success(studentService.selectALLList());
    }






}
