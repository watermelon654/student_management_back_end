package com.student_management.demo.mapper.mysql.menu;

import com.student_management.demo.mapper.dataobject.menu.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper
@Repository
public interface MenuMapper {
    Set<Menu> selectMenuByRoleId(Long id) ;
}
