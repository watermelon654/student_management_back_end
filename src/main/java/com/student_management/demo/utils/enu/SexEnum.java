package com.student_management.demo.utils.enu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexEnum {

    /** 男 */
    MALE(1),
    /** 女 */
    FEMALE(0);

    /**
     * 性别
     */
    private final Integer sex;

}

