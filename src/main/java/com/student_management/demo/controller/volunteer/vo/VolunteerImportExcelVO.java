package com.student_management.demo.controller.volunteer.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * 服务时长 Excel 导入 VO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class VolunteerImportExcelVO {

    @ExcelProperty("学号")
    private String stuNum;

    @ExcelProperty("姓名")
    private String stuName;

    @ExcelProperty("服务时长")
    private int time;

    /**
     * id
     */
    @ExcelProperty("id")
    private Long id;
}

