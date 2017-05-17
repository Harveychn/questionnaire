package com.questionnaire.ssm.module.resultAnalysis.service;

import com.questionnaire.ssm.module.resultAnalysis.pojo.DisplayAnswerPaperVO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.ListAnswerPaperVO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.ListPrimaryDataInfoVO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.MissionQuestionnaireVO;

import java.util.List;

/**
 * Created by 95884 on 2017/5/7.
 */
public interface PrimaryDataService {
    List<ListPrimaryDataInfoVO> listPrimaryData()throws Exception;
    List<ListAnswerPaperVO> listAnswerPaper(MissionQuestionnaireVO missionQuestionnaireVO)throws Exception;
    DisplayAnswerPaperVO getAnswerPaper(Long answerPaperId)throws Exception;
}
