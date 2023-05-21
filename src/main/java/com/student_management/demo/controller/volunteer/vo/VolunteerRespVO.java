package com.student_management.demo.controller.volunteer.vo;

import lombok.Data;

@Data
public class VolunteerRespVO {

    /**
     * 学号
     */
    private Long stuNum;

    /**
     * 姓名
     */
    private String stuName;

//    /**
//     * 评价状态
//     *
//     */
//    private Integer status;
    /**
     * 服务时长
     */
    private int time;

    /**
     * 备注
     */
    //private String note;

}
