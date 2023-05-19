package com.student_management.demo.mapper.mysql.staff;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.mapper.dataobject.staff.StaffDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StaffMapper extends BaseMapper<StaffDO> {
    /**
     * 按照职工号查询职工
     * @param num
     * @return
     */
    default StaffDO selectStaffByNum(String num) {
        QueryWrapper<StaffDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("num", num);
        return selectOne(wrapper);
    }

}
