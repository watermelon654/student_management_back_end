package com.student_management.demo.mapper.mysql.service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.mapper.dataobject.science.ScienceDO;
import com.student_management.demo.mapper.dataobject.service.ServiceDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ServiceMapper extends BaseMapper<ServiceDO>{

    /**
     * 按照学号查询Science
     * @param stu_num
     * @return
     */
    default ServiceDO selectServiceByStuNum(String stu_num) {
        QueryWrapper<ServiceDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stuNum", stu_num);
        return selectOne(wrapper);
    }

    /**
     * 按照学生id查询Science
     * @param stu_id
     * @return
     */
    default ServiceDO selectServiceByStuId(Long stu_id) {
        QueryWrapper<ServiceDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stuId", stu_id);
        return selectOne(wrapper);
    }

    default List<ServiceDO> selectAllList() {
        QueryWrapper<ServiceDO> wrapper = new QueryWrapper<>();
        return selectList(wrapper);
    }
}
