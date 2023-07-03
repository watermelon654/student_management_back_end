package com.student_management.demo.controller.grade.vo.Student;

import lombok.Data;

@Data
public class StudentGradeRespVO {

    /**
     * 学号
     */
    private String stuNum;

    /**
     * 姓名
     */
    private String stuName;

    /**
     * GPA
     */
    private float gpa;

}
