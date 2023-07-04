package com.student_management.demo.mapper.mysql.summary;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.student_management.demo.mapper.dataobject.summary.SummaryDO;

import java.util.List;

@Mapper
@Repository
public interface SummaryMapper extends BaseMapper<SummaryDO> {

    /**
     * 按照学号查询Summary
     * @param stu_num
     * @return
     */
    default SummaryDO selectSummaryByStuNum(String stu_num) {
        QueryWrapper<SummaryDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stuNum", stu_num).eq("isDel", 0);
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
        wrapper.eq("stuId", stu_id).eq("isDel", 0);
        return selectOne(wrapper);
    }

    default List<SummaryDO> selectListByStatus(boolean status) {
        QueryWrapper<SummaryDO> wrapper = new QueryWrapper<>();
        //查询条件: status值为status，isdel值为0
        wrapper.eq("status", status).eq("isDel", 0);

        return selectList(wrapper);
    }

    default List<SummaryDO> selectAllList() {
        QueryWrapper<SummaryDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("isDel", 0);
        return selectList(wrapper);
    }

    int updateGpaByStuNum(SummaryDO summary);

    int updateVolByStuNum(SummaryDO summary);

    /**
     * 根据学生学号获取当前学生GPA打分结果
     *
     * @param stuNum
     * @return gpa score
     */
    Integer getGpaScoreByStuNum(String stuNum);

    /**
     * 根据学生学号获取当前学生Vol打分结果
     *
     * @param stuNum
     * @return vol score
     */
    Integer getVolScoreByStuNum(String stuNum);

    /**
     * 删除选定学生记录
     *
     * @param stuNum
     * @return gpa score
     */
    void updateAllGradeAsNull(List<String> stuNum);
    void updateIsDel(List<String> nums);
    void refreshUpdateTime(List<String> stuNum);
}
