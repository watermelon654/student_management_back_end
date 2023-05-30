package com.student_management.demo.mapper.dataobject.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicDO {
    private String name;//姓名
    private String num;//学号/职工号
    private Long id;//id
    private List<Long> permission;
    private List<Long> role;
}
