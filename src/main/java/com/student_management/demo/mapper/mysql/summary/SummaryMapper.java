package com.student_management.demo.mapper.mysql.summary;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.student_management.demo.mapper.dataobject.summary.SummaryDO;

import java.util.Collection;

public interface SummaryMapper extends BaseMapper<SummaryDO> {

    /**
     * 按照学号查询Summary
     * @param stu_num
     * @return
     */
    default SummaryDO selectSummaryByStuNum(String stu_num) {
        QueryWrapper<SummaryDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stu_num", stu_num);
        return selectOne(wrapper);
    }

    /**
     * 按照学生id查询Summary
     * @param stu_id
     * @return
     */
    default SummaryDO selectSummaryByStuId(Long stu_id) {
        QueryWrapper<SummaryDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stu_id", stu_id);
        return selectOne(wrapper);
    }
    /**
     * 批量插入，适合大量数据插入
     *
     * @param entities 实体们
     */
//    default void insertBatch(Collection<SummaryDO> entities) {
//        Db.saveBatch(entities);
//    }
//
//    default void updateBatch(SummaryDO update) {
//        update(update, new QueryWrapper<>());
//    }


}
