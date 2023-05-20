package com.student_management.demo.controller.summary.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@TableName(value="summary")//数据库表名
public class SummarySelectListRespVO {
    private List<SummaryBaseVO> summarylist;

}
