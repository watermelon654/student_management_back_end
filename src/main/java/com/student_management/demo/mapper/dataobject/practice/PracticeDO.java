package com.student_management.demo.mapper.dataobject.practice;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value="Practice")//数据库表名
public class PracticeDO {
    /**
     * id
     *
     */
    @TableId(type = IdType.AUTO)//设为自增
    private Long id;

    /**
     * 学生id
     */
    private Long stuId;

    /**
     * 学号
     */
    private String stuNum;

    /**
     * 姓名
     */
    private String stuName;


    private String title;
    private String director;
    private String constitution;
    private String content;
    private String time;




    private String result;

    private Long createUserId;
    private Long updateUserId;
}
