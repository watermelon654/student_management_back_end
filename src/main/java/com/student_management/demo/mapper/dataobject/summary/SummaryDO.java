package com.student_management.demo.mapper.dataobject.summary;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

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
    private Long stu_id;
    /**
     * 学生学号
     */
    private String stu_num;
    /**
     * 学生姓名
     */
    private String stu_name;
    /**
     * 学生GPA
     */
    private Double gpa;
    /**
     * 学生志愿
     */
    private Double vol;
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
}
