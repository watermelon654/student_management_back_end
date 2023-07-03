package com.student_management.demo;

import com.student_management.demo.mapper.dataobject.summary.SummaryDO;
import com.student_management.demo.mapper.mysql.summary.SummaryMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
@SpringBootTest
public class SummaryTests {
    @Autowired
    private SummaryMapper summaryMapper;

    @Test
    void selectSummaryByStatusNotNull() {
        List<SummaryDO> list =  summaryMapper.selectListByStatus(true);
        Assertions.assertDoesNotThrow(() -> {
            System.out.println("result >>> " + list);
            // 断言查询结果为非空
            Assertions.assertNotNull(list);
        });
    }
}
