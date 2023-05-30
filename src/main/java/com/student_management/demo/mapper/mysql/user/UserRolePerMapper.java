package com.student_management.demo.mapper.mysql.user;

import com.student_management.demo.mapper.dataobject.user.UserRolePermissionDO;

import java.util.List;

public interface UserRolePerMapper {
    List<UserRolePermissionDO> findPerRoleIdByUserId(Long userId);
}
