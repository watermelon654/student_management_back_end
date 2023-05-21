package com.student_management.demo.controller.volunteer.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.student_management.demo.controller.summary.vo.SummaryBaseVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@TableName(value="volunteer")//数据库表名
public class VolunteerSelectListRespVO {
    private List<VolunteerBaseVO> volunteerlist;
}
