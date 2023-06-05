package com.student_management.demo.mapper.dataobject.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPermissionDO extends UserDo{
    private List<Long> roles;
    private List<String> urls;
}
