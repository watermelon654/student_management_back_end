package com.student_management.demo.service.menu;

import com.student_management.demo.mapper.dataobject.menu.Menu;
import com.student_management.demo.mapper.mysql.menu.MenuMapper;
import com.student_management.demo.service.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("MenuService")
@Slf4j
public class MenuServiceImpl implements MenuService{
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RedisService redisService;
    public Set<Menu> getMenu(String num) {
        Set<String> roleIds = redisService.getList("user_roles_" + num);
        Set<Menu> menus = new HashSet<>();
        for (String roleId: roleIds) {
            menus.addAll(menuMapper.selectMenuByRoleId(Long.parseLong(roleId)));
        }
        return menus;
    }

}
