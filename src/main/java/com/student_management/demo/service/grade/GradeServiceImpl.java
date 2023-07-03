package com.student_management.demo.service.grade;
import cn.hutool.core.collection.CollUtil;
import com.student_management.demo.controller.grade.vo.Judge.*;
import com.student_management.demo.controller.grade.vo.Student.StudentGradeRespVO;
import com.student_management.demo.convert.grade.GradeConvert;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.mysql.grade.GradeMapper;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import com.student_management.demo.service.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
@Service("gradeService")
@Slf4j
public class GradeServiceImpl implements GradeService{
    @Resource
    private GradeMapper gradeMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private RedisService redisService;
    //--------------------------------------
    //评委端

    /**
     * 根据学生学号查询当前学生是否已在grade表中删除
     *
     * @param stuNum
     * @return 查询结果，true表示已删除
     */
    public Boolean isDeleted(String stuNum){
        if (gradeMapper.isDeleted(stuNum) == 1 ){
            return true;
        }
        return false;
    }

    /**
     * 批量导入GPA，如果已经存在则强制更新
     *
     * @param importGrade     导入GPA列表
     * @return 导入结果
     */
    public GradeImportRespVO importGradeList(List<GradeImportExcelVO> importGrade, String judgeNum) {
        GradeImportRespVO respVO = GradeImportRespVO.builder().createGradenames(new ArrayList<>())
                .updateGradenames(new ArrayList<>()).failureGradenames(new LinkedHashMap<>()).build();
        //列表为空
        if (CollUtil.isEmpty(importGrade)) {
            respVO.setEmpty(true);
            return respVO;
        }
        //获取评委id
        Long operateId = Long.parseLong(redisService.getValue("user_id_" + judgeNum));
        //对每一个表项检查
        importGrade.forEach(grade -> {
            Long stuId;
            // 判断是否在学生信息表stu_info中，在进行插入
            StudentDO existStu = studentMapper.selectStudentByNum(grade.getStuNum());
            if (existStu == null) {
                // 如果学生表中不存在，在学生表中插入记录
                studentMapper.insertStudentBasicInfo(grade.getStuName(),grade.getStuNum());
                gradeMapper.createStuUpdateInfo(operateId,grade.getStuNum());
            }
            // 获取stu_id，判断是否在学生成绩表grade中，在进行插入
            existStu = studentMapper.selectStudentByNum(grade.getStuNum());
            GradeDO existGrade = gradeMapper.selectGradeByStuNum(grade.getStuNum());
            if (existGrade == null) {
                // 如果在成绩表中不存在，在成绩表插入记录
                GradeDO createGrade = GradeConvert.INSTANCE.convert(grade);
                createGrade.setStuId(existStu.getId());
                // 使用当前操作者的用户id作为创建和更新id
                createGrade.setCreateUserId(operateId);
                createGrade.setUpdateUserId(operateId);
                gradeMapper.insert(createGrade);
                respVO.getCreateGradenames().add(grade.getStuName());
                return;
            }

            // 如果存在，更新成绩表中的记录
            GradeDO updateGrade = GradeConvert.INSTANCE.convert(grade);

            // 检查字段是否发生变化，并只更新有变化的字段
            if (updateGrade.getGpa() != existGrade.getGpa()) {
                GradeExcelUpdateVO gradeExcelUpdateVO = new GradeExcelUpdateVO();
                gradeExcelUpdateVO.setStuId(existGrade.getId());
                gradeExcelUpdateVO.setId(operateId);
                gradeExcelUpdateVO.setGpa(grade.getGpa());
                gradeMapper.updateGradeUploadInfo(gradeExcelUpdateVO);
            }


//            respVO.getUpdateGradenames().add(grade.getStuName());
        });
        return respVO;
    }

    /**
     * 获得未删除学生的GPA
     *
     * @return 全体未删除学生的GPA
     */
    @Override
    public GradeSelectListRespVO selectAllStudents() {
        List<GradeDO> listdo = gradeMapper.selectAllStudents();
        GradeSelectListRespVO respVO = new GradeSelectListRespVO();
        List<JudgeGradeRespVO> listvo = convertList(listdo);
        respVO.setGradelist(listvo);
        return respVO;
    }

    /**
     * 将GradeDO复制给JudgeGradeRespVO并添加score
     *
     * @param listdo
     * @return listvo
     */
    public List<JudgeGradeRespVO> convertList(List<GradeDO> listdo) {
        List<JudgeGradeRespVO> listvo = new ArrayList<>();
        for (GradeDO gradeDO : listdo) {
            JudgeGradeRespVO vo = new JudgeGradeRespVO();
            vo.setStuNum(gradeDO.getStuNum());
            vo.setStuName(gradeDO.getStuName());
            vo.setGpa(gradeDO.getGpa());
            vo.setCreateTime(gradeDO.getCreateTime());
            vo.setUpdateTime(gradeDO.getUpdateTime());

            // 从summary表中获取score数据
            Integer score = gradeMapper.getGpaScoreByStuNum(gradeDO.getStuNum());
            vo.setScore(score);

            listvo.add(vo);
        }
        return listvo;
    }

    /**
     * 显示当前学生GPA打分结果
     *
     * @param gradeScore
     * @return 打分结果
     */
    @Override
    public boolean updateResult(GradeScoreReqVO gradeScore) {
        boolean result = false;
        if (gradeMapper.updateGPAScore(gradeScore) > 0 &&
                gradeMapper.updateGradeUpdateInfo(gradeScore) > 0 &&
                gradeMapper.updateSummaryUpdateInfo(gradeScore) > 0) {
            result = true;
        }
        return result;
    }

/*    *//**
     * 显示当前删除结果
     *
     * @param
     * @return 删除结果
     *//*
    public boolean showDeleteResult(String judgeNum, String stuNum) {
        boolean result = false;
        GradeScoreReqVO gradeScore = new GradeScoreReqVO();
        gradeScore.setJudgeNum(judgeNum);
        gradeScore.setStuNum(judgeNum);
        if (gradeMapper.deleteByStuNum(stuNum) > 0 &&
                gradeMapper.updateGradeUpdateInfo(gradeScore) > 0){
            result = true;
        }
        return result;
    }*/
    //--------------------------------------
    //学生端

    /**
     * 根据学生学号获取当前学生学号、姓名、GPA
     *
     * @param stuNum
     * @return 当前学号、姓名、GPA
     */
    public StudentGradeRespVO getInfoByStuNum(String stuNum) {
        return gradeMapper.getInfoByStuNum(stuNum);
    }
}
