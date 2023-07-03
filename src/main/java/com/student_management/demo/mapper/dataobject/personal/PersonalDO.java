package com.student_management.demo.mapper.dataobject.personal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value="personal")//数据库表名
public class PersonalDO {
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



    private String school;
    private String society;
    private String self;

    private boolean isDel;

}
