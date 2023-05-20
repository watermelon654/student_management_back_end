package com.student_management.demo.mapper.mysql.personal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.mapper.dataobject.personal.PersonalDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PersonalMapper extends BaseMapper<PersonalDO> {
    /**
     * 按照学号查询Personal
     * @param stu_num
     * @return
     */
    default PersonalDO selectPersonalByStuNum(String stu_num) {
        QueryWrapper<PersonalDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stuNum", stu_num);
        return selectOne(wrapper);
    }

    /**
     * 按照学生id查询Personal
     * @param stu_id
     * @return
     */
    default PersonalDO selectPersonalByStuId(Long stu_id) {
        QueryWrapper<PersonalDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stuId", stu_id);
        return selectOne(wrapper);
    }
}
