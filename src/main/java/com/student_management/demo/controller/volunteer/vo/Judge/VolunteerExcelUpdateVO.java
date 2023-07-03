package com.student_management.demo.controller.volunteer.vo.Judge;

import lombok.Data;

@Data
public class VolunteerExcelUpdateVO {
    /**
     * 学生
     */
    private long stuId;

    /**
     * 志愿服务时长
     */
    private int time;

    /**
     * 更新者
     */
    private long id;
}
