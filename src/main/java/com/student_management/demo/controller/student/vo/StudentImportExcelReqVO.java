
package com.student_management.demo.controller.student.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName(value="stu_info")//数据库表名
public class StudentImportExcelReqVO {

    /**
     * 学号
     */
    @ExcelProperty("ID")
    private String num;

    /**
     * 姓名
     */
    @ExcelProperty("name")
    private String name;

    /**
     * 性别
     */
    @ExcelProperty("sex")
    private Integer sex;

    /**
     * 邮箱
     */
    @ExcelProperty("mailbox")
    private String email;

    /**
     * 学年ID
     */
    @ExcelProperty("year")
    private Long yearId;

    /**
     * 专业 ID
     */
    @ExcelProperty("major")
    private Long majorId;

    /**
     * 班级 ID
     */
    @ExcelProperty("class")
    private Long classId;


}
