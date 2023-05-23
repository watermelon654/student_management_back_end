package com.student_management.demo.service.user;

import com.student_management.demo.controller.user.vo.UserBasicRespVO;
import com.student_management.demo.mapper.dataobject.staff.StaffDO;
import com.student_management.demo.mapper.mysql.staff.StaffMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("StaffBasicServce")
@Slf4j
public class StaffBasicServiceImpl implements StaffBasicService{
    @Resource
    private StaffMapper staffMapper;

    @Override
    public UserBasicRespVO getBasicInfo(String num, boolean role) {
        StaffDO staffDO = staffMapper.selectStaffByNum(num);
        UserBasicRespVO respVO = new UserBasicRespVO();
        respVO.setUsername(staffDO.getName());
        respVO.setNum(staffDO.getNum());
        respVO.setRole(role);
        respVO.setId(staffDO.getId());
        return respVO;
    }
}
