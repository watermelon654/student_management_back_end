package com.student_management.demo.mapper.mysql.volunteer;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.controller.volunteer.vo.Student.StudentVolunteerRespVO;
import com.student_management.demo.controller.volunteer.vo.Judge.VolunteerScoreReqVO;
import com.student_management.demo.mapper.dataobject.volunteer.VolunteerDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VolunteerMapper extends BaseMapper<VolunteerDO>{

    //--------------------------------------
    //评委端

    int isDeleted(String stuNum);

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
     * 查看所有未删除学生的志愿服务时长
     *
     * @return 未删除学生列表
     */
    default List<VolunteerDO> selectAllStudents() {
        QueryWrapper<VolunteerDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isDel", 0);
        return selectList(queryWrapper);
//        return selectList(null);
    }

    Integer getVolScoreByStuNum(String stuNum);

    /**
     * 根据学生学号更新当前学生志愿服务时长打分
     *
     * @param volunteerScore
     * @return 打分结果，大于0表示打分成功，等于0表示打分失败
     */
    int updateVolunteerScore(VolunteerScoreReqVO volunteerScore);


    //--------------------------------------
    //学生端

    /**
     * 根据学生学号获取当前学生志愿服务时长信息
     *
     * @param stuNum
     * @return 当前学生StudentVolunteerRespVO：学号，姓名，志愿服务时长
     */
    StudentVolunteerRespVO getInfoByStuNum(String stuNum);
}
