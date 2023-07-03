package com.student_management.demo.mapper.mysql.practice;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.mapper.dataobject.practice.PracticeDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PracticeMapper extends BaseMapper<PracticeDO>{

    /**
     * 按照学号查询Practice
     * @param stu_num
     * @return
     */
      default List<PracticeDO> selectPracticeByStuNum(String stu_num) {
        QueryWrapper<PracticeDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stuNum", stu_num).eq("isDel", 0);
        return selectList(wrapper);
    }

    /**
     * 按照学生id查询Practice
     * @param stu_id
     * @return
     */
    default PracticeDO selectPracticeByStuId(Long stu_id) {
        QueryWrapper<PracticeDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stuId", stu_id);
        return selectOne(wrapper);
    }

    default List<PracticeDO> selectAllList() {
        QueryWrapper<PracticeDO> wrapper = new QueryWrapper<>();
         wrapper.inSql("stuId", "SELECT id FROM stu_info WHERE isDel = 0");
        wrapper.eq("isDel", 0);
        return selectList(wrapper);
    }
}
