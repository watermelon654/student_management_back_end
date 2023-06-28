package com.student_management.demo.mapper.mysql.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.dataobject.user.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<StudentDO> {
    void insertUserRole(UserRoleDO userRoleDO);
}
