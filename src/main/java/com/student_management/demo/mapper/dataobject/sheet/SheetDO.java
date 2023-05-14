package com.student_management.demo.mapper.dataobject.sheet;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 本来设计的是所有评价表的父类，但是跑的时候报错了，
 * 属性没继承上，目前就是不继承，后面完善的时候再看怎么回事
 */
@Data
public class SheetDO {
    /**
     * id
     *
     */
    @TableId(type = IdType.AUTO)//设为自增
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
