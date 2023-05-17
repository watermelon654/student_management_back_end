package com.student_management.demo.mapper.mysql.service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.mapper.dataobject.service.ServiceDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ServiceMapper extends BaseMapper<ServiceDO>{

    /**
     * 按照学号查询Service
     * @param stu_num
     * @return
     */
    default ServiceDO selectServiceByStuNum(String stu_num) {
        QueryWrapper<ServiceDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stu_num", stu_num);
        return selectOne(wrapper);
    }

    /**
     * 按照学生id查询Service
     * @param stu_id
     * @return
     */
    default ServiceDO selectServiceByStuId(Long stu_id) {
        QueryWrapper<ServiceDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stu_id", stu_id);
        return selectOne(wrapper);
    }
    int insertService(Long stuId,Long stuNum,String stuName, String title,String director,String constitution,String content,String time,String result, int score,int status);
}
