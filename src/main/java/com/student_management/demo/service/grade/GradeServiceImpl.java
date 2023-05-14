package com.student_management.demo.service.grade;
import cn.hutool.core.collection.CollUtil;
import com.student_management.demo.controller.grade.vo.GradeCreateReqVO;
import com.student_management.demo.controller.grade.vo.GradeImportExcelVO;
import com.student_management.demo.controller.grade.vo.GradeImportRespVO;
import com.student_management.demo.convert.grade.GradeConvert;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;
import com.student_management.demo.mapper.mysql.grade.GradeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
@Service("gradeService")
@Slf4j
public class GradeServiceImpl implements GradeService{
    @Resource
    private GradeMapper gradeMapper;

    /**
     * 批量导入GPA，如果已经存在则强制更新
     *
     * @param importGrade     导入GPA列表
     * @return 导入结果
     */
    public GradeImportRespVO importGradeList(List<GradeImportExcelVO> importGrade) {
        GradeImportRespVO respVO = GradeImportRespVO.builder().createGradenames(new ArrayList<>())
                .updateGradenames(new ArrayList<>()).failureGradenames(new LinkedHashMap<>()).build();
        //列表为空
        if (CollUtil.isEmpty(importGrade)) {
            respVO.setEmpty(true);
            return respVO;
        }
        //对每一个表项检查
        importGrade.forEach(grade -> {
            // 判断如果不存在，在进行插入
            GradeDO existGrade = gradeMapper.selectGradeByNum(grade.getStu_num());
            if (existGrade == null) {
                gradeMapper.insert(GradeConvert.INSTANCE.convert(grade));
                respVO.getCreateGradenames().add(grade.getStu_name());
                return;
            }
            // 如果存在，更新
            GradeDO updateGrade = GradeConvert.INSTANCE.convert(grade);
            updateGrade.setId(existGrade.getId());
            gradeMapper.updateById(updateGrade);
            respVO.getUpdateGradenames().add(grade.getStu_name());
        });
        return respVO;
    }

    /**
     * 学生端查看成绩
     * @param reqVO 学生信息
     * @return
     */
    @Override
    public GradeDO searchGrade(GradeCreateReqVO reqVO) {
        return gradeMapper.selectGradeByNum(reqVO.getStu_num());
    }

    /**
     * 评委老师修改GPA信息
     * @param reqVO 用户信息
     */
    @Override
    public void updateGrade(GradeCreateReqVO reqVO) {

    }
}
