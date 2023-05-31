package com.student_management.demo.mapper.mysql.student;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ClassMapper {
    Long selectIdByName(String name);
}
