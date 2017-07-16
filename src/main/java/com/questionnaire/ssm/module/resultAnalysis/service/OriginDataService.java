package com.questionnaire.ssm.module.resultAnalysis.service;

import com.questionnaire.ssm.module.resultAnalysis.pojo.DisplayAnswerPaperVO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.AnswerPaperVO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.OriginDataInfoVO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.MissionQuestionnaireVO;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 95884 on 2017/5/7.
 */
public interface OriginDataService {

    List<OriginDataInfoVO> listPrimaryData() throws Exception;

    List<AnswerPaperVO> listAnswerPaper(MissionQuestionnaireVO missionQuestionnaireVO) throws Exception;

    @Transactional
    DisplayAnswerPaperVO getAnswerPaper(Long launchQesId, Long answerPaperId) throws Exception;

}
