package com.student_management.demo.mapper.mysql.volunteer;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.mapper.dataobject.volunteer.VolunteerDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface VolunteerMapper extends BaseMapper<VolunteerDO>{

    /**
     * 按照学号查询志愿服务时长
     * @param stu_num
     * @return
     */
    default VolunteerDO selectVolunteerByStuNum(String stu_num) {
        QueryWrapper<VolunteerDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stu_num", stu_num);
        return selectOne(wrapper);
    }

    /**
     * 按照学生id查询志愿服务时长
     * @param stu_id
     * @return
     */
    default VolunteerDO selectVolunteerByStuId(Long stu_id) {
        QueryWrapper<VolunteerDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stu_id", stu_id);
        return selectOne(wrapper);
    }

}
