package com.student_management.demo.mapper.mysql.summary;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.controller.summary.vo.SummaryVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.student_management.demo.mapper.dataobject.summary.SummaryDO;
@Mapper
@Repository
public interface SummaryMapper extends BaseMapper<SummaryDO> {

    //    /**
//     * 按照学号查询学生id
//     * @param num
//     * @return
//     */
//    default StudentDO selectStudentByNum(String num) {
//        QueryWrapper<StudentDO> wrapper = new QueryWrapper<>();
//        //查询条件
//        wrapper.eq("num", num);
//        return selectOne(wrapper);
//    }

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
     * 查询所有评分，根据学生id集合
     * @param num
     * @return
     */
    default List<SummaryVO> selectSummary(List<Long> list) {

        QueryWrapper<SummaryVO> wrapper = new QueryWrapper<>();
        wrapper.in("stu_id",list);

        return selectList(wrapper);
    }


    default int update(SummaryVO summaryVO) {
        QueryWrapper<SummaryVO> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_num",summaryVO.getStuNum());

        return update(summaryVO,wrapper);
    }



    /**
     * 插入学生基本信息用于生成学生id
     * @param name 姓名
     * @param num 学号
     * @return
     */
    int insertStudentBasicInfo(String name, String num);




}
