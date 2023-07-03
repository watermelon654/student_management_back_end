package com.student_management.demo.controller.grade.vo.Judge;

import lombok.Data;

@Data
public class GradeExcelUpdateVO {
    /**
     * 学生
     */
    private long stuId;

    /**
     * GPA
     */
    private float gpa;

    /**
     * 更新者
     */
    private long id;
}
