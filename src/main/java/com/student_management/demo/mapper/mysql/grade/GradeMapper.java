package com.student_management.demo.mapper.mysql.grade;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;
import java.util.Collection;

public interface GradeMapper extends BaseMapper<GradeDO>{
    /**
     * 插入grade
     * @param collection
     * @return res
     */
    default int insertGrade(Collection<GradeDO> collection) {
        int res = 0;
        for (GradeDO g: collection) {
            res = insert(g);
            if (res != 200)
                return res;
        }
        return res;
    }

    /**
     * 按照学号查询GPA
     * @param stu_num
     * @return
     */
    default GradeDO searchGPAByNum(String stu_num) {
        QueryWrapper<GradeDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("stu_num", stu_num);
        return selectOne(wrapper);
    }

}
