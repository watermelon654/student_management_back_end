package com.student_management.demo.mapper.mysql.volunteer;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.controller.volunteer.vo.VolunteerRespVO;
import com.student_management.demo.mapper.dataobject.volunteer.VolunteerDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        wrapper.eq("stuNum", stu_num);
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
        wrapper.eq("stuId", stu_id);
        return selectOne(wrapper);
    }

    /**
     * 查看全部学生志愿服务时长
     *
     * @return 全部学生列表
     */
    default List<VolunteerDO> selectAllStudents() {
        return selectList(null);
    }

    /**
     * 打分结果
     *
     * @param volunteer
     * @return 打分结果，大于0表示打分成功，等于0表示打分失败
     */
    int updateByStuNum(VolunteerDO volunteer);

    /**
     * 根据学生学号获取当前学生志愿服务时长信息
     *
     * @param stuNum
     * @return 学号，姓名，志愿服务时长
     */
    VolunteerRespVO getInfoByStuNum(String stuNum);
}
