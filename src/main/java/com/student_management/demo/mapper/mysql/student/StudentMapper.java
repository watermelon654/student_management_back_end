package com.student_management.demo.mapper.mysql.student;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.controller.summary.vo.SummaryVO;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface StudentMapper extends BaseMapper<StudentDO> {
    /**
     * 按照学号查询学生id
     * @param num
     * @return
     */
    default StudentDO selectStudentByNum(String num) {
        QueryWrapper<StudentDO> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("num", num);
        return selectOne(wrapper);
    }




    /**
     * 查询所有学生
     * @param num
     * @return
     */
    default List<StudentDO> selectStudent() {
        QueryWrapper<StudentDO> wrapper = new QueryWrapper<>();
        //查询条件
//        wrapper.eq("num", num);
        return selectList(wrapper);
    }

    /**
     * 查询所有学生
     * @param num
     * @return
     */
    default List<Long> selectStudentbystatus() {
        List<Long> idList = new ArrayList<>();
        QueryWrapper<StudentDO> wrapper = new QueryWrapper<>();
        wrapper.eq("status","0");
        //查询条件
//        wrapper.eq("num", num);
        List<StudentDO> studentDOS = selectList(wrapper);

        if (studentDOS.size() != 0 ){

            for (StudentDO studentDO : studentDOS) {
                Long getid = studentDO.getId();
                idList.add(getid);
            }

        }
        return idList;

    }


    default List<Long> selectStudentbystatus1() {
        List<Long> idList = new ArrayList<>();
        QueryWrapper<StudentDO> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1");
        //查询条件
//        wrapper.eq("num", num);
        List<StudentDO> studentDOS = selectList(wrapper);

        if (studentDOS.size() != 0 ){

            for (StudentDO studentDO : studentDOS) {
                Long getid = studentDO.getId();
                idList.add(getid);
            }

        }
        return idList;

    }

    default int updateStu (StudentDO studentDO){
        QueryWrapper<StudentDO> wrapper = new QueryWrapper<>();
        wrapper.eq("id",studentDO.getId());
        return update(studentDO,wrapper);
    }




    /**
     * 插入学生基本信息用于生成学生id
     * @param name 姓名
     * @param num 学号
     * @return
     */
    int insertStudentBasicInfo(String name,String num);





    default void deleteByIds(List<Long> idList) {
        deleteBatchIds(idList);
    }






}
