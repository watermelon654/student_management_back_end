package com.student_management.demo.controller.grade.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.student_management.demo.controller.volunteer.vo.VolunteerBaseVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@TableName(value="grade")//数据库表名
public class GradeSelectListRespVO {
    private List<GradeBaseVO> gradelist;
}
