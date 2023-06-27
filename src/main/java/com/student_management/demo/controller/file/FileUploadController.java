package com.student_management.demo.controller.file;


import com.student_management.demo.common.CommonResult;
import com.student_management.demo.controller.file.vo.FileUploadReqVO;
import com.student_management.demo.service.file.FileService;
import com.student_management.demo.utils.token.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api")
public class FileUploadController {
    @Resource
    private FileService fileService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @PostMapping("/pdf/import")
    @ResponseBody
    @ApiOperation("上传文件")
    public CommonResult<?> uploadFile(@RequestHeader("Authorization") String authHeader,
                                      @RequestPart(value = "file") MultipartFile file,
                                      @RequestPart(value = "subject") String subject) {
        String stuNum = jwtTokenUtil.getUsernameFromToken(authHeader);//id,且学生和老师id不会重复
        return fileService.upload(stuNum, file, subject);
    }

}

