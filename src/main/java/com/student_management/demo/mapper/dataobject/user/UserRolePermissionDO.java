package com.student_management.demo.mapper.dataobject.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRolePermissionDO {
    private Long userId;
    private Long roleId;
    private Long permissionId;
}
