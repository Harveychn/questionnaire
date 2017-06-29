package com.questionnaire.ssm.module.resultAnalysis.service;

import com.questionnaire.ssm.module.resultAnalysis.pojo.DisplayAnswerPaperVO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.AnswerPaperVO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.PrimaryDataInfoVO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.MissionQuestionnaireVO;

import java.util.List;

/**
 * Created by 95884 on 2017/5/7.
 */
public interface PrimaryDataService {
    List<PrimaryDataInfoVO> listPrimaryData() throws Exception;

    List<AnswerPaperVO> listAnswerPaper(MissionQuestionnaireVO missionQuestionnaireVO) throws Exception;

    DisplayAnswerPaperVO getAnswerPaper(Long answerPaperId) throws Exception;
}
