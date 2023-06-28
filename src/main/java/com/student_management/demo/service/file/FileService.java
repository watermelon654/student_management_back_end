package com.student_management.demo.service.file;

import com.student_management.demo.common.CommonResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    CommonResult<?> upload(String num, MultipartFile file,String subject);

    void preview(String stuNum, String subject, HttpServletResponse response);
}
