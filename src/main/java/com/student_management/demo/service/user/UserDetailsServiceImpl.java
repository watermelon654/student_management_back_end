package com.student_management.demo.service.user;

import com.student_management.demo.mapper.dataobject.user.UserPermissionDO;
import com.student_management.demo.mapper.mysql.user.UserMapper;
import com.student_management.demo.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.student_management.demo.common.error.ErrorCodeConstants.*;
import static com.student_management.demo.utils.exception.ServiceExceptionUtil.exception;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("根据用户名查询尝试登录的管理员信息，用户名=" + username);
        UserPermissionDO userPermissionDO = userMapper.selectUserInfoByUserNum(username);
        System.out.println("通过持久层进行查询，结果=" + userPermissionDO);

        if (userPermissionDO == null) {
            System.out.println("根据用户名没有查询到有效的管理员数据，将抛出异常");
            throw exception(AUTH_LOGIN_USER_NOT_EXIST);
        }

        System.out.println("查询到匹配的管理员数据，需要将此数据转换为UserDetails并返回");
        UserDetails userDetails = User.builder()
                .username(userPermissionDO.getName())
                .password(userPermissionDO.getPasswd())
                .accountExpired(false)
                .accountLocked(false)
                .disabled(userPermissionDO.isDel())
                .credentialsExpired(false)
                .authorities(userPermissionDO.getUrls().toArray(new String[] {}))
                .build();
        System.out.println("转换得到UserDetails=" + userDetails);

        //将信息存入redis中
        redisService.setValue("user_id_" + username, userPermissionDO.getId() + "");
        redisService.setValue("user_name_" + username, userPermissionDO.getName());
        redisService.setValue("user_num_" + username, userPermissionDO.getNum());
        redisService.setValue("user_role_" + username, String.valueOf(userPermissionDO.getRoles().contains(1l)));
        for (Long roleId:userPermissionDO.getRoles()) {
            redisService.addList("user_roles_" + username, String.valueOf(roleId));
        }
        redisService.setValue("user_permission_" + username, userPermissionDO.getUrls().toString());
        return userDetails;
    }

}