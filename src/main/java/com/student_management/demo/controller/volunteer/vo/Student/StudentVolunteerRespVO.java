package com.student_management.demo.controller.volunteer.vo.Student;

import lombok.Data;

@Data
public class StudentVolunteerRespVO {

    /**
     * 学号
     */
    private String stuNum;

    /**
     * 姓名
     */
    private String stuName;

    /**
     * 服务时长
     */
    private int time;

}
