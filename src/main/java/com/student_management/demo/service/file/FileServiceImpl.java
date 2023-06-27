package com.student_management.demo.service.file;

import com.student_management.demo.common.CommonResult;
import com.student_management.demo.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.student_management.demo.common.error.ErrorCodeConstants.*;
import static com.student_management.demo.utils.exception.ServiceExceptionUtil.exception;
@Service("File")
public class FileServiceImpl implements FileService {
    @Value("${file.upload-path}")
    private String uploadPath;

    @Value("${file.allowed-types}")
    private String allowedTypes;

    @Value("${file.max-size}")
    private long maxFileSize;
    // 允许的科目
    private final String SUBJECTS = "science,practice,service";


    /**
     * 上传pdf
     * @param num
     * @param file
     * @return
     */
    @Override
    public CommonResult<?> upload(String num, MultipartFile file, String subject) {
        if (file == null || file.isEmpty()) {
            throw exception(EMPTY_FILE);
        }
        if (!StringUtils.hasText(uploadPath)) {
            throw exception(EMPTY_FILE_PATH);
        }

        if (!isFileTypeAllowed(file)) {
            throw exception(ERROR_FILE_FORMAT);
        }

        if (file.getSize() > maxFileSize) {
            throw exception(ERROR_FILE_SIZE);
        }
        if (!isSubjectVaild(subject)) {
            throw exception(ERROR_SUBJECT);
        }

        try {
            if (!isFileContentValid(file)) {
                throw exception(ERROR_FILE_CONTENT);
            }
            // 文件名格式：uuid_subject
            String fileName = generateUniqueFileName(file.getOriginalFilename(),subject);
            String fileParentPath = uploadPath + File.separator + num;
            // 文件上传路径：根目录+学号
            String filePath =  fileParentPath + File.separator +fileName;
            File dest = new File(filePath);
            // 获取文件所在目录
            File directory = dest.getParentFile();
            // 检查目录是否存在
            if (!directory.exists()) {
                // 创建目录
                directory.mkdirs();
            }
            else if (dest.exists()){
                // 检查文件是否已经存在，如果已经存在，直接覆盖
                dest.delete();
            }
            file.transferTo(dest);

            // 将文件名保存到数据库中
            return CommonResult.success("文件上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            throw exception(UPLOAD_FILE_FAILED);
        }
    }

    private boolean isFileTypeAllowed(MultipartFile file) {
        String fileExtension = getFileExtension(file.getOriginalFilename());
        if (fileExtension != null ) {
            //List<String> allowedExtensions = Arrays.asList(allowedTypes);
            return allowedTypes.contains(fileExtension.toLowerCase());
        }
        return false;
    }

    private boolean isFileContentValid(MultipartFile file) throws IOException {
        // 验证文件类型是否为PDF
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("application/")) {
            return false;
        }

        // 进一步验证文件内容，例如使用文件头、魔术数字等方式

        // 示例：验证文件内容为pdf
        byte[] fileBytes = file.getBytes();

        // PDF 文件的文件头：25 50 44 46 2D 31 2E
        if (fileBytes.length >= 4) {
            byte[] pdfHeader = {(byte) 0x25, (byte) 0x50, (byte) 0x44, (byte) 0x46, (byte) 0x2D, (byte) 0x31, (byte) 0x2E };
            return startsWith(fileBytes, pdfHeader);
        }
        return false;
    }

    private boolean startsWith(byte[] array, byte[] prefix) {
        if (array.length < prefix.length) {
            return false;
        }
        for (int i = 0; i < prefix.length; i++) {
            if (array[i] != prefix[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 重新生成文件名
     * @param originalFileName
     * @return
     */
    private String generateUniqueFileName(String originalFileName, String subject) {
        String fileExtension = getFileExtension(originalFileName);
        String uniqueFileName = UUID.randomUUID() +"_"+subject;
        if (fileExtension != null) {
            uniqueFileName += "." + fileExtension;
        }
        return uniqueFileName;
    }

    /**
     * 检查科目中类型是否与subject相符
     * @param subject
     * @return
     */
    private boolean isSubjectVaild(String subject) {
        List<String> subjects = Arrays.asList(SUBJECTS.split(","));
        return subjects.contains(subject);
    }

    private String getFileExtension(String fileName) {
        if (StringUtils.hasText(fileName)) {
            int dotIndex = fileName.lastIndexOf(".");
            if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
                return fileName.substring(dotIndex + 1);
            }
        }
        return null;
    }
}
