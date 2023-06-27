package com.student_management.demo.service.file;

import com.student_management.demo.common.CommonResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    CommonResult<?> upload(String num, MultipartFile file,String subject);
}
