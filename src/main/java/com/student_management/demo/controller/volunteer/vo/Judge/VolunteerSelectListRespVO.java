package com.student_management.demo.controller.volunteer.vo.Judge;

import com.baomidou.mybatisplus.annotation.TableName;
import com.student_management.demo.controller.volunteer.vo.Judge.JudgeVolunteerRespVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@TableName(value="volunteer")//数据库表名
public class VolunteerSelectListRespVO {
    private List<JudgeVolunteerRespVO> volunteerlist;
}
