
package com.student_management.demo.controller.summary.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName(value="summary")//数据库表名
public class SummaryVO{

    public String getStuNum(){
        return this.stuNum;
    }




    /**
     * id
     */
    private Long id;

    /**
     * 学号
     */
//    @TableField(value = "stu_id")
    private Long stuId;

    /**
     * 姓名
     */
//    @TableField(value = "stu_num")
    private String stuNum;

    /**
     * 性别
     *
     */
//    @TableField(value = "stu_name")
    private String stuName;

    /**
     * 邮箱
     */
    private Float gpa;

    /**
     * 加密后的密码
     */
    private Float vol;

    /**
     * 学年 ID
     */
    private Integer sci;

    /**
     * 专业 ID
     */
    private Integer pra;

    /**
     * 班级 ID
     */
    private Integer ser;



    /**
     * 评价状态
     *
     * */
    private Integer per;

}
