package com.questionnaire.ssm.module.resultAnalysis.mapper;

import com.questionnaire.ssm.module.resultAnalysis.pojo.*;

import java.util.List;

/**
 * Created by 95884 on 2017/5/5.
 * Description: 结果分析模块mapper
 * 查询问卷完成量原始数据
 */
public interface ResultAnalysisMapper {

    List<PrimaryDataInfoVO> listPrimaryDataInfo() throws Exception;

    Long listCount(PrimaryDataInfoVO primaryDataInfoVO) throws Exception;

    List<AnswerPaperVO> listAnswerPaper(MissionQuestionnaireVO missionQuestionnaireVO) throws Exception;

    String selectAnswerDetail(QuestionAnswerPaperVO questionAnswerPaperVO) throws Exception;
}
