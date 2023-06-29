package com.student_management.demo.service.user;

import com.student_management.demo.common.CommonResult;
import com.student_management.demo.controller.user.vo.UserBasicRespVO;
import com.student_management.demo.mapper.dataobject.user.UserDo;
import com.student_management.demo.mapper.mysql.user.UserMapper;
import com.student_management.demo.service.redis.RedisService;
import com.student_management.demo.utils.token.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;;import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service("UserBasicService")
@Slf4j
public class UserBasicServiceImpl implements UserBasicService {
    @Resource
    RedisService redisService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private UserDetailsServiceImpl userDetailsService;
    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncryptionService passwordEncryptionService;

    public UserBasicRespVO getBasicInfo(String num) {
        Set roles = redisService.getList("user_roles_" + num);
        return new UserBasicRespVO(
                num,
                redisService.getValue("user_name_" + num),
                redisService.getValue("user_role_" + num),
                roles.contains("8")?"true":"false");
    }

    /**
     * 获取当前登录用户的基本信息
     * @param token
     * @return
     */
    public HashMap<String,String> getCurrentUserInfo(String token){
        String id = jwtTokenUtil.getUsernameFromToken(token);//学号/职工号
        HashMap<String,String> map = new HashMap<>();
        map.put("id", redisService.getValue("user_id_" + id));
        map.put("name",  redisService.getValue("user_name_" + id));
        map.put("num", redisService.getValue("user_num_" + id));
        return map;
    }

    /**
     * 更新密码（原密码为原始密码）
     * @param username
     * @param newPassword
     * @return
     */
    @Override
    public CommonResult<?> changePasswd(String username, String newPassword) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String encryptedPassword = passwordEncryptionService.encryptPassword(newPassword);
        if (newPassword.equals("123456")) {
            return CommonResult.error(500,"密码不可为原始密码，请重新设置！");
        }
        // 如果密码为原始密码或学号，允许更新
        if (passwordEncryptionService.matches("123456",userDetails.getPassword()) ||
                passwordEncryptionService.matches(username, userDetails.getPassword()) ) {
            // 更新用户密码的逻辑
            return updatePasswd(username, encryptedPassword);

        } else {
            return CommonResult.error(500,"无权限修改密码，请联系学工办！");
        }
    }

    /**
     * 重设密码为学号
     * @param num
     * @return
     */
    @Override
    public CommonResult<?> resetPasswd(String num) {
        String encryptedPassword = passwordEncryptionService.encryptPassword(num);
        // 重设密码为num
        return updatePasswd(num, encryptedPassword);
    }
    /**
     * 更新密码
     * @param username
     * @param encryptedPassword
     * @return
     */
    private CommonResult<?> updatePasswd(String username, String encryptedPassword) {
        UserDo userDo = userMapper.selectUserInfoByUserNum(username);
        if (userDo != null) {
            HashMap map = new HashMap();
            map.put("num", username);
            map.put("passwd", encryptedPassword);
            map.put("updateTime",new Timestamp(System.currentTimeMillis()));
            map.put("initial",false);
            userMapper.updateUserPasswdByStuNum(map);
            userMapper.updateUserPasswdByStaffNum(map);
        } else {
            // 处理用户不存在的情况
            return CommonResult.error(500, "用户不存在！");
        }
        return CommonResult.success("修改密码成功！请重新登录！");
    }




}
