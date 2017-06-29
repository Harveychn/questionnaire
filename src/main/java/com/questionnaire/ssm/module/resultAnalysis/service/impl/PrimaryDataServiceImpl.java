package com.questionnaire.ssm.module.resultAnalysis.service.impl;

import com.questionnaire.ssm.module.generated.mapper.*;
import com.questionnaire.ssm.module.generated.pojo.*;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.resultAnalysis.mapper.ResultAnalysisMapper;
import com.questionnaire.ssm.module.resultAnalysis.pojo.*;
import com.questionnaire.ssm.module.resultAnalysis.service.PrimaryDataService;
import com.questionnaire.ssm.module.resultAnalysis.util.AnswerPaperVODOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 95884 on 2017/5/5.
 */
@Service
public class PrimaryDataServiceImpl implements PrimaryDataService {
    /**
     * 获取问卷信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<PrimaryDataInfoVO> listPrimaryData() throws Exception {
        List<PrimaryDataInfoVO> result = new ArrayList<>();
        List<PrimaryDataInfoVO> primaryDataInfoVOS = null;
        try {
            primaryDataInfoVOS = resultAnalysisMapper.listPrimaryDataInfo();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, "获取问卷失败");
        }
        //获取问卷完成数量
        Long count = null;
        for (PrimaryDataInfoVO primaryDataInfoVO : primaryDataInfoVOS) {
            count = resultAnalysisMapper.listCount(primaryDataInfoVO);
            primaryDataInfoVO.setQuestionnaireCount(count);
            try {
                result.add(primaryDataInfoVO);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    /**
     * 获取问卷对应答卷信息
     *
     * @param missionQuestionnaireVO
     * @return
     * @throws Exception
     */
    @Override
    public List<AnswerPaperVO> listAnswerPaper(MissionQuestionnaireVO missionQuestionnaireVO) throws Exception {
        List<AnswerPaperVO> answerPaperVOS = new ArrayList<>();
        try {
            answerPaperVOS = resultAnalysisMapper.listAnswerPaper(missionQuestionnaireVO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, "获取答卷失败");
        }
        return answerPaperVOS;
    }

    /**
     * 根据答卷ID查询展示答卷
     *
     * @param answerPaperId 答卷ID
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public DisplayAnswerPaperVO getAnswerPaper(Long answerPaperId) throws Exception {
        AnswerPaper answerPaperDO = new AnswerPaper();
        try {
            answerPaperDO = answerPaperMapper.selectByPrimaryKey(answerPaperId);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        if (answerPaperDO == null) {
            throw new OperateDBException(CodeForVOEnum.DB_SELECT_FAIL, DBTableEnum.ANSWER_PAPER.getTableName());
        }
        DisplayAnswerPaperVO displayAnswerPaperVO = AnswerPaperVODOUtil.toDisplayAnswerPaperVO(answerPaperDO);

        Questionnaire questionnaire = new Questionnaire();
        long questionnaireId = displayAnswerPaperVO.getQuestionnaireId();
        questionnaire = questionnaireMapper.selectByPrimaryKey(questionnaireId);
        displayAnswerPaperVO.setQuestionnaireTitle(questionnaire.getQuestionnaireTitle());
        displayAnswerPaperVO.setQuestionnaireSubtitle(questionnaire.getQuestionnaireSubtitle());
        displayAnswerPaperVO.setQuestionnaireDescription(questionnaire.getQuestionnaireDescription());
        /*根据查询出来的问卷ID查询问题*/
        List<MappingQuestionnaireQuestion> mapDOList = null;
        MappingQuestionnaireQuestionExample mapDOExample = new MappingQuestionnaireQuestionExample();
        mapDOExample.createCriteria().andQuestionnaireIdEqualTo(questionnaireId);
        try {
            mapDOList = mappingQuestionnaireQuestionMapper.selectByExample(mapDOExample);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, DBTableEnum.MAPPING_QUESTIONNAIRE_QUESTION.getTableName());
        }

        Long currentQuestionId = null;
        List<AnswerQuestionVO> answerQuestionVOList = new ArrayList<>();
        QuestionWithBLOBs questionWithBLOBs = null;
        String answerDetail = null;
        for (int order = 0; order < mapDOList.size(); order++) {
            currentQuestionId = mapDOList.get(order).getQuestionId();//获取当前问题id
            questionWithBLOBs = questionMapper.selectByPrimaryKey(currentQuestionId);//获取当前问题标题内容

            QuestionAnswerPaperVO questionAnswerPaperVO = new QuestionAnswerPaperVO();
            questionAnswerPaperVO.setAnswerPaperId(answerPaperId);
            questionAnswerPaperVO.setQuestionId(currentQuestionId);

            answerDetail = resultAnalysisMapper.selectAnswerDetail(questionAnswerPaperVO);//获取答案

//            answerDetailString= QesManageVODOUtil.toAnswerString(currentAnswerDetail,questionWithBLOBs.getQuestionType());

            answerQuestionVOList.add(order, AnswerPaperVODOUtil.toAnswerQuestionVO(questionWithBLOBs, answerDetail));
        }
        displayAnswerPaperVO.setAnswerQuestions(answerQuestionVOList);
        return displayAnswerPaperVO;
    }

    private ResultAnalysisMapper resultAnalysisMapper;
    private AnswerPaperMapper answerPaperMapper;
    private QuestionMapper questionMapper;
    private MappingQuestionnaireQuestionMapper mappingQuestionnaireQuestionMapper;
    private QuestionnaireMapper questionnaireMapper;
    private static final Logger logger = LoggerFactory.getLogger(PrimaryDataServiceImpl.class);

    @Autowired
    public PrimaryDataServiceImpl(ResultAnalysisMapper resultAnalysisMapper,
                                  AnswerPaperMapper answerPaperMapper,
                                  QuestionMapper questionMapper,
                                  MappingQuestionnaireQuestionMapper mappingQuestionnaireQuestionMapper,
                                  QuestionnaireMapper questionnaireMapper) {
        this.resultAnalysisMapper = resultAnalysisMapper;
        this.answerPaperMapper = answerPaperMapper;
        this.questionMapper = questionMapper;
        this.mappingQuestionnaireQuestionMapper = mappingQuestionnaireQuestionMapper;
        this.questionnaireMapper = questionnaireMapper;
    }
}