package com.student_management.demo.mapper.dataobject.science;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value="science")//数据库表名
public class ScienceDO {
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
    private Integer level;
    private String time;
    private String result;

    private Long createUserId;
    private Long updateUserId;

    private boolean isDel;
}
