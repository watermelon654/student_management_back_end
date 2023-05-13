package com.student_management.demo.mapper.dataobject.student;

import com.student_management.demo.mapper.dataobject.user.UserDo;

public class StudentDO extends UserDo {

    /**
     * 学年 ID
     */
    private Long year_id;

    /**
     * 专业 ID
     */
    private Long major_id;

    /**
     * 班级 ID
     */
    private Long class_id;

    /**
     * 评价状态
     *
     * */
    private Integer status;

}
