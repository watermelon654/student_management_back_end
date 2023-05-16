package com.student_management.demo.controller.volunteer.vo;

import lombok.Data;

@Data
public class VolunteerRespVO {

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
    /**
     * 服务时长
     */
    private int time;

    /**
     * 备注
     */
    //private String note;

}
