package com.student_management.demo.service.grade;
import cn.hutool.core.collection.CollUtil;
import com.student_management.demo.controller.grade.vo.*;
import com.student_management.demo.convert.grade.GradeConvert;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;
import com.student_management.demo.mapper.dataobject.student.StudentDO;
import com.student_management.demo.mapper.mysql.grade.GradeMapper;
import com.student_management.demo.mapper.mysql.student.StudentMapper;
import com.student_management.demo.mapper.mysql.summary.SummaryMapper;
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

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private SummaryMapper summaryMapper;


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
            // 判断是否在学生信息表stu_info中，在进行插入
            StudentDO existStu = studentMapper.selectStudentByNum(grade.getStuNum());
            if (existStu == null) {
                // 如果学生表中不存在，在学生表中插入记录
                studentMapper.insertStudentBasicInfo(grade.getStuName(),grade.getStuNum());
            }
            // 获取stu_id，判断是否在学生成绩表grade中，在进行插入
            existStu = studentMapper.selectStudentByNum(grade.getStuNum());
            GradeDO existGrade = gradeMapper.selectGradeByStuNum(grade.getStuNum());
            if (existGrade == null) {
                // 如果在成绩表中不存在，在成绩表插入记录
                GradeDO createGrade = GradeConvert.INSTANCE.convert(grade);
                createGrade.setStuId(existStu.getId());
                gradeMapper.insert(createGrade);
                respVO.getCreateGradenames().add(grade.getStuName());
                return;
            }
            // 如果存在，更新成绩表中的记录
            GradeDO updateGrade = GradeConvert.INSTANCE.convert(grade);
            updateGrade.setId(existGrade.getId());
            gradeMapper.updateById(updateGrade);
            respVO.getUpdateGradenames().add(grade.getStuName());
        });
        return respVO;
    }

    /**
     * 获得全体学生GPA
     *
     * @return 全体学生GPA
     */
    @Override
    public GradeSelectListRespVO selectAllStudents() {
        List<GradeDO> listdo = gradeMapper.selectAllStudents();
        GradeSelectListRespVO respVO = new GradeSelectListRespVO();
        List<GradeBaseVO> listvo = convertList(listdo);
        respVO.setGradelist(listvo);
        return respVO;
    }

//    /**
//     * 打分结果
//     *
//     * @param grade
//     * @return 打分结果，true表示打分成功，false表示打分失败
//     */
//    public boolean updateResult(GradeDO grade) {
//        return gradeMapper.updateByStuNum(grade) > 0;
//    }

    /**
     * 根据学生学号获取当前学生学号、姓名、GPA
     *
     * @param stuNum
     * @return 当前学号、姓名、GPA
     */
    public GradeRespVO getInfoByStuNum(String stuNum) {
        return gradeMapper.getInfoByStuNum(stuNum);
    }

    /**
     * 显示当前学生GPA打分结果
     *
     * @param gradeScore
     * @return 打分结果
     */
    @Override
    public boolean updateResult(GradeScoreReqVO gradeScore) {
        return gradeMapper.updateGPAScore(gradeScore) > 0;
    }

    /**
     * 将GradeDO复制给GradeBaseVO并添加score
     *
     * @param listdo
     * @return listvo
     */
    public List<GradeBaseVO> convertList(List<GradeDO> listdo) {
        List<GradeBaseVO> listvo = new ArrayList<>();
        for (GradeDO gradeDO : listdo) {
            GradeBaseVO vo = new GradeBaseVO();
            vo.setStuNum(gradeDO.getStuNum());
            vo.setStuName(gradeDO.getStuName());
            vo.setGpa(gradeDO.getGpa());

            // 从summary表中获取score数据
            Integer score = gradeMapper.getGpaScoreByStuNum(gradeDO.getStuNum());
            System.out.println("score:" + score);
            vo.setScore(score);

            listvo.add(vo);
        }
        return listvo;
    }


    /**
     * 根据学生学号查询当前学生是否已在grade表中删除
     *
     * @param stuNum
     * @return 学号，姓名，gpa
     */
    public Boolean isDeleted(String stuNum){
        if (gradeMapper.isDeleted(stuNum) == 1 ){
            return true;
        }
        return false;
    };
}
