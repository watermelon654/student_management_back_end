package com.student_management.demo.service.user;

import com.student_management.demo.controller.user.vo.UserBasicRespVO;
import com.student_management.demo.convert.user.UserConvert;
import com.student_management.demo.mapper.dataobject.staff.StaffDO;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.dataobject.user.UserBasicDO;
import com.student_management.demo.mapper.dataobject.user.UserRolePermissionDO;
import com.student_management.demo.mapper.mysql.staff.StaffMapper;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import com.student_management.demo.mapper.mysql.user.UserRolePerMapper;
import com.student_management.demo.service.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("UserBasicService")
@Slf4j
public class UserBasicServiceImpl implements UserBasicService {
    @Resource
    private UserRolePerMapper urpMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private StaffMapper staffMapper;
    @Resource
    RedisService redisService;

    public UserBasicRespVO getBasicInfo(Long id) {
        List<UserRolePermissionDO> list = urpMapper.findPerRoleIdByUserId(id);
        List<Long> roles = new ArrayList<>(), permissions = new ArrayList<>();
        for (UserRolePermissionDO urp: list) {
            roles.add(urp.getRoleId());
            permissions.add(urp.getPermissionId());
        }
        String name;
        boolean isStudent = roles.contains(1L);
        if (isStudent){//检查角色中是否有学生
            StudentDO studentDO = studentMapper.selectById(id);
            name = studentDO.getName();
        } else {
            StaffDO staffDO = staffMapper.selectById(id);
            name = staffDO.getName();
        }
        // 将角色和权限信息存储到Redis中
        redisService.setValue("user_name_" + id, name);
        redisService.setValue("user_roles_" + id, roles.toString());
        redisService.setValue("user_roles_" + id, roles.toString());

        return new UserBasicRespVO(name, isStudent);
    }



}
