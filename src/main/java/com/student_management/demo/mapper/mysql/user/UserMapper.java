package com.student_management.demo.mapper.mysql.user;

import com.student_management.demo.mapper.dataobject.user.UserPermissionDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Mapper
@Repository
public interface UserMapper {
    UserPermissionDO selectUserInfoByUserNum(String num);
    void updateUserPasswdByStuNum(HashMap map);
    void  updateUserPasswdByStaffNum(HashMap map);

    int selectUserInitialByUserNum(String num);

}
