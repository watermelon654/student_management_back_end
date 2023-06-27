package com.student_management.demo.controller.file.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileUploadReqVO {
    private MultipartFile file;
    private String uploadType;
    private String subject;
}
