
package com.student_management.demo.controller.stu;

import com.student_management.demo.CommonResult;
import com.student_management.demo.controller.stu.vo.StudentVo;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import com.student_management.demo.mapper.mysql.student.StudentMapper1;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stu/")
@Api(tags = "EasyExcel")
public class StuController {

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private StudentMapper1 studentMapper1;

    /**
     * 上传GPA excel表格
     * @param file
     * @throws IOException
     */
//    @PostMapping("/upload")
//    public CommonResult<GradeImportRespVO> importUserExcel(@RequestPart(value = "file") MultipartFile file) throws IOException {
//        List<GradeImportExcelVO> userList = ExcelUtils.read(file,GradeImportExcelVO.class);
//        GradeImportRespVO respVO = service.importGradeList(userList);
//        // 检查上传文件是否为空文件
//        if (respVO.isEmpty())
//            return CommonResult.error(500, "文件内容为空！");
//        return CommonResult.success(respVO);
//    }

    @PostMapping("/upload")
    public CommonResult<List<StudentDO>> upload(@RequestBody List<StudentDO> list) throws IOException {

        if (list.size() != 0 ){

            List<StudentDO> studentDOS = studentMapper.selectStudent();
            List<Long> ids = new ArrayList<>();
            if (studentDOS.size() != 0) {

                for (StudentDO student : studentDOS) {
                    ids.add(student.getId());
                }
            }

            if (ids.size() != 0 && ids != null){
                studentMapper.deleteByIds(ids);
            }



            for (StudentDO studentDO : list) {

                StudentVo studentVo = new StudentVo();

                studentVo.setNum(studentDO.getNum());
                studentVo.setClass_id(studentDO.getClassId());
                studentVo.setEmail(studentDO.getEmail());
                studentVo.setMajor_id(studentDO.getMajorId());
                studentVo.setName(studentDO.getName());
                studentVo.setSex(studentDO.getSex());
                studentVo.setYear_id(studentDO.getYearId());



                studentMapper1.insert(studentVo);

            }

        }


        List<StudentDO> studentDOS = studentMapper.selectStudent();
        return CommonResult.success(studentDOS);

    }





    @RequestMapping("/selectall")
    public CommonResult<List<StudentDO>> selectall() throws IOException {

        List<StudentDO> studentDOS = studentMapper.selectStudent();
        return CommonResult.success(studentDOS);
    }






}
