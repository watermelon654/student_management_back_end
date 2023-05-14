package com.student_management.demo.mapper.dataobject.grade;

import com.student_management.demo.mapper.dataobject.sheet.SheetDO;
import lombok.Data;

@Data
public class GradeDO {
    /**
     * GPA
     */
    private float gpa;

    /**
     * 备注
     */
    private String note;

    /**
     * id
     */
    private Long id;

    /**
     * 学生id
     */
    private Long stu_id;

    /**
     * 学号
     */
    private Long stu_num;

    /**
     * 姓名
     */
    private String stu_name;

    /**
     * 评价状态
     *
     */
    private Integer status;
}
