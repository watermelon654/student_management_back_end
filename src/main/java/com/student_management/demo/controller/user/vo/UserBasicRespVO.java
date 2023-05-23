package com.student_management.demo.controller.user.vo;

import lombok.Data;

@Data
public class UserBasicRespVO {
    private boolean role;//false:学生，true：职工
    private String username;//姓名
    private String num;//学号/职工号
    private Long id;//id
}
