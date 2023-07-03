package com.student_management.demo.convert.grade;

import com.student_management.demo.controller.grade.vo.GradeBaseVO;
import com.student_management.demo.controller.grade.vo.GradeCreateReqVO;
import com.student_management.demo.controller.grade.vo.GradeImportExcelVO;
import com.student_management.demo.controller.grade.vo.GradeRespVO;
import com.student_management.demo.controller.volunteer.vo.VolunteerBaseVO;
import com.student_management.demo.controller.volunteer.vo.VolunteerRespVO;
import com.student_management.demo.mapper.dataobject.grade.GradeDO;

import com.student_management.demo.mapper.dataobject.volunteer.VolunteerDO;
import com.student_management.demo.mapper.mysql.summary.SummaryMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface GradeConvert {
    GradeConvert INSTANCE = Mappers.getMapper(GradeConvert.class);

    GradeDO convert(GradeCreateReqVO bean);
    GradeCreateReqVO convert(GradeDO bean);
    GradeDO convert(GradeImportExcelVO grade);

    //List<GradeBaseVO> convertList2(List<GradeDO> list);
}
