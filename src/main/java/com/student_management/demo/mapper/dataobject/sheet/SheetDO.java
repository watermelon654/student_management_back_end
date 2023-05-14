package com.student_management.demo.mapper.dataobject.sheet;

import lombok.Data;

@Data
public class SheetDO {
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

    /**
     * 评分/gpa/志愿时长
     */
    //private Float score;
}
