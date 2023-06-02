package com.student_management.demo.service.user;

import com.student_management.demo.controller.user.vo.UserBasicRespVO;
import com.student_management.demo.service.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;;

@Service("UserBasicService")
@Slf4j
public class UserBasicServiceImpl implements UserBasicService {
    @Resource
    RedisService redisService;

    public UserBasicRespVO getBasicInfo(String id) {
        System.out.println("id:"+id);
        System.out.println("name"+redisService.getValue("user_name_" + id));
        return new UserBasicRespVO(
                redisService.getValue("user_name_" + id),
                redisService.getValue("user_role_" + id));
    }



}
