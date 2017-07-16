package com.questionnaire.ssm.module.resultAnalysis.service.impl;

import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.questionnaireManage.util.QesManageVODOUtil;
import com.questionnaire.ssm.module.resultAnalysis.mapper.ResultAnalysisMapper;
import com.questionnaire.ssm.module.resultAnalysis.pojo.*;
import com.questionnaire.ssm.module.resultAnalysis.service.OriginDataService;
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
public class OriginDataServiceImpl implements OriginDataService {
    /**
     * 获取问卷信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<OriginDataInfoVO> listPrimaryData() throws Exception {
        List<OriginDataInfoVO> result = new ArrayList<>();
        List<OriginDataInfoVO> originDataInfoVOS = null;
        try {
            originDataInfoVOS = resultAnalysisMapper.listPrimaryDataInfo();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, "获取问卷失败");
        }
        //获取问卷完成数量
        Long count = null;
        for (OriginDataInfoVO originDataInfoVO : originDataInfoVOS) {
            count = resultAnalysisMapper.listCount(originDataInfoVO);
            originDataInfoVO.setQuestionnaireCount(count);
            try {
                result.add(originDataInfoVO);
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
        List<AnswerPaperVO> answerPaperVOS = null;
        try {
            answerPaperVOS = resultAnalysisMapper.listAnswerPaper(missionQuestionnaireVO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, "获取答卷失败");
        }
        return answerPaperVOS;
    }

    /**
     * 查询答卷详细信息
     *
     * @param launchQesId   发布的问卷ID
     * @param answerPaperId 答卷ID
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public DisplayAnswerPaperVO getAnswerPaper(Long launchQesId, Long answerPaperId) throws Exception {
        DisplayAnswerPaperVO displayAnswerPaperVO = resultAnalysisMapper.selectAnswerPaperByIds(launchQesId, answerPaperId);
        if (displayAnswerPaperVO == null) {
            return null;
        }
        //未处理的答卷详细信息
        List<AnswerQuestionVO> answerQesDOS = resultAnalysisMapper.listAnswerDetailByIds(launchQesId, answerPaperId);
        if (answerQesDOS.size() > 0) {
            for (AnswerQuestionVO curAnswerDetailDO : answerQesDOS) {
                curAnswerDetailDO.setQuestionType(
                        QesManageVODOUtil.parse2VOQuestionType(curAnswerDetailDO.getQuestionType()));
                if (curAnswerDetailDO.getAnswerDetail().equals(CONSTANT.getNullAnswerString())) {
                    curAnswerDetailDO.setAnswerDetail(CONSTANT.getVoNullAnswerString());
                }
            }
            //设置处理之后的答卷详细信息
            displayAnswerPaperVO.setAnswerQuestions(answerQesDOS);
        } else {//不存在问题的情况，基本不会出现，出现则有异常
            return null;
        }
        return displayAnswerPaperVO;
    }

    private ResultAnalysisMapper resultAnalysisMapper;
    private static final Logger logger = LoggerFactory.getLogger(OriginDataServiceImpl.class);

    @Autowired
    public OriginDataServiceImpl(ResultAnalysisMapper resultAnalysisMapper) {
        this.resultAnalysisMapper = resultAnalysisMapper;
    }
}