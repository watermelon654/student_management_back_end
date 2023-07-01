package com.student_management.demo.mapper.dataobject.volunteer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value="volunteer")//数据库表名
public class VolunteerDO {
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

    /**
     * 志愿服务时长
     */
    private int time;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;
}
