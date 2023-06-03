package com.student_management.demo.mapper.mysql.practice;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.mapper.dataobject.practice.PracticeDO;
import com.student_management.demo.mapper.dataobject.science.ScienceDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PracticeMapper extends BaseMapper<PracticeDO>{

    /**
     * 按照学号查询Practise
     * @param stu_num
     * @return
     */
    default PracticeDO selectPracticeByStuNum(String stu_num) {
        QueryWrapper<PracticeDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stuNum", stu_num);
        return selectOne(wrapper);
    }

    /**
     * 按照学生id查询Practise
     * @param stu_id
     * @return
     */
    default PracticeDO selectPracticeByStuId(Long stu_id) {
        QueryWrapper<PracticeDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stuId", stu_id);
        return selectOne(wrapper);
    }
    int insertPractice(Long stuId,String stuNum,String stuName, String title,String director,String constitution,String content,String time,String result);


    default List<PracticeDO> selectAllList() {
        QueryWrapper<PracticeDO> wrapper = new QueryWrapper<>();
        return selectList(wrapper);
    }

}
