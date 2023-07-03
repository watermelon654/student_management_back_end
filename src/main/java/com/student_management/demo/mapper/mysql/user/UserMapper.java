package com.student_management.demo.mapper.mysql.user;

import com.student_management.demo.mapper.dataobject.user.UserPermissionDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    UserPermissionDO selectUserInfoByUserNum(String num);

}
