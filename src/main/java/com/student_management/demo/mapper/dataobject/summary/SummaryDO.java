package com.student_management.demo.mapper.dataobject.summary;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.sql.Timestamp;

@TableName("summary")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummaryDO {
    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 学生ID
     */
    private Long stuId;
    /**
     * 学生学号
     */
    private String stuNum;
    /**
     * 学生姓名
     */
    private String stuName;
    /**
     * 学生GPA
     */
    private Integer gpa;
    /**
     * 学生志愿
     */
    private Integer vol;
    /**
     * 学生科研
     */
    private Integer sci;
    /**
     * 学生社会实践
     */
    private Integer pra;
    /**
     * 学生骨干
     */
    private Integer ser;
    /**
     * 学生个人学年总结
     */
    private Integer per;
    /**
     * 状态
     */
    private boolean status;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

}
