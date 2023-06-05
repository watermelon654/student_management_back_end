package com.student_management.demo.service.menu;

import com.student_management.demo.mapper.dataobject.menu.Menu;

import java.util.Set;

public interface MenuService {
    Set<Menu> getMenu(String num);
}
