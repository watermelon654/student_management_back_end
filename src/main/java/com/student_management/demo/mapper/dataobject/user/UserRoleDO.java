package com.student_management.demo.mapper.dataobject.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRoleDO {
    private Long userId;
    private Long roleId;
}
