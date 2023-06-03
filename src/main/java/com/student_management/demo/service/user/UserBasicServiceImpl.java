package com.student_management.demo.service.user;

import com.student_management.demo.controller.user.vo.UserBasicRespVO;
import com.student_management.demo.service.redis.RedisService;
import com.student_management.demo.utils.token.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;;import java.util.HashMap;

@Service("UserBasicService")
@Slf4j
public class UserBasicServiceImpl implements UserBasicService {
    @Resource
    RedisService redisService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public UserBasicRespVO getBasicInfo(String id) {
        return new UserBasicRespVO(
                redisService.getValue("user_name_" + id),
                redisService.getValue("user_role_" + id));
    }

    /**
     * 获取当前登录用户的基本信息
     * @param token
     * @return
     */
    protected HashMap<String,String> getCurrentUserInfo(String token){
        String id = jwtTokenUtil.getUsernameFromToken(token);//id,且学生和老师id不会重复
        HashMap<String,String> map = new HashMap<>();
        map.put("id", id);
        map.put("name",  redisService.getValue("user_name_" + id));
        map.put("num", redisService.getValue("user_num_" + id));
        return map;
    }


}
