package com.questionnaire.ssm.module.resultAnalysis.mapper;

import com.questionnaire.ssm.module.resultAnalysis.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 95884 on 2017/5/5.
 * Description: 结果分析模块mapper
 * 查询问卷完成量原始数据
 */
public interface ResultAnalysisMapper {

    List<OriginDataInfoVO> listPrimaryDataInfo() throws Exception;

    Long listCount(OriginDataInfoVO originDataInfoVO) throws Exception;

    List<AnswerPaperVO> listAnswerPaper(MissionQuestionnaireVO missionQuestionnaireVO) throws Exception;

    List<String> selectAnswerDetail(QuestionAnswerPaperVO questionAnswerPaperVO) throws Exception;


    /**
     * 根据编号，查询答卷信息
     *
     * @param launchQesId         发布的问卷ID
     * @param answerPaperID 答卷ID
     * @return
     * @throws Exception
     */
    DisplayAnswerPaperVO selectAnswerPaperByIds(@Param("launchQesId") long launchQesId, @Param("answerPaperID") long answerPaperID)
    throws Exception;

    /**
     * 根据编号，查询答卷详细信息
     *
     * @param launchQesId         发布的问卷ID
     * @param answerPaperID 答卷ID
     * @return
     * @throws Exception
     */
    List<AnswerQuestionVO> listAnswerDetailByIds(@Param("launchQesId") long launchQesId, @Param("answerPaperID") long answerPaperID)
            throws Exception;
}
