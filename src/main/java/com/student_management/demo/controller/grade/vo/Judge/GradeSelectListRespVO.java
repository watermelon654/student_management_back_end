package com.student_management.demo.controller.grade.vo.Judge;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@TableName(value="grade")//数据库表名
public class GradeSelectListRespVO {
    private List<JudgeGradeRespVO> gradelist;
}
