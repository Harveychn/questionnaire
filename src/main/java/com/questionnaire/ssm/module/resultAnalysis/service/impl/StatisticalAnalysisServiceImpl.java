package com.questionnaire.ssm.module.resultAnalysis.service.impl;

import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.questionnaireManage.enums.QuestionTypeEnum;
import com.questionnaire.ssm.module.questionnaireManage.util.QesManageVODOUtil;
import com.questionnaire.ssm.module.resultAnalysis.mapper.StatisticalAnalysisMapper;
import com.questionnaire.ssm.module.resultAnalysis.pojo.*;
import com.questionnaire.ssm.module.resultAnalysis.service.StatisticalAnalysisService;
import com.questionnaire.ssm.module.resultAnalysis.util.StatisticalAnalysisVODOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 郑晓辉 on 2017/5/30.
 * Description:
 */
@Service
public class StatisticalAnalysisServiceImpl implements StatisticalAnalysisService {

    @Override
    public List<StatisticalAnalysisResultVO> getQesPaperAnalyzeResult(String missionId, String missionPaperId) throws Exception {
        List<AnswerInfoDealedDTO> answerInfoDealedDTOS = getAnswerInfoDOList(missionId, missionPaperId);
        //无答卷提交信息
        if (answerInfoDealedDTOS == null || answerInfoDealedDTOS.size() <= 0) {
            return null;
        }
        //问卷中所有问题所有答项信息，答项选中值初始为0
        QesQuestionInfoDTO qesQuestionInfoDTO = getQuestionAnalyzeDTOList(missionPaperId);
        if (qesQuestionInfoDTO == null) {
            return null;
        }
        List<QuestionAnalyzeDTO> questionAnalyzeDTOList = qesQuestionInfoDTO.getQuestionAnalyzeDTOS();

        Map<Long, List<QuestionOptionAnalyzeDTO>> questionOptionMap = qesQuestionInfoDTO.getQuestionOptionMap();


        //当前问题id的答项信息
        List<QuestionOptionAnalyzeDTO> curQesIdStandardOptionList = null;
        List<String> answerSelectedOptions = null;
        for (AnswerInfoDealedDTO answerSelectedDTO : answerInfoDealedDTOS) {
            //调查问卷中不包含当前问题id的信息
            if (!questionOptionMap.containsKey(answerSelectedDTO.getQuestionId())) {
                continue;
            }
            //当前问卷原始答项信息
            curQesIdStandardOptionList = questionOptionMap.get(answerSelectedDTO.getQuestionId());
            if (curQesIdStandardOptionList.size() <= 0) {
                continue;
            }
            //当前问卷选中的答项信息
            answerSelectedOptions = answerSelectedDTO.getAnswerOptions();
            if (answerSelectedOptions.size() <= 0) {
                continue;
            }
            for (QuestionOptionAnalyzeDTO curOptionAnalyzeStandardDTO : curQesIdStandardOptionList) {
                for (String selectedOption : answerSelectedOptions) {
                    if (curOptionAnalyzeStandardDTO.getOptionContent().equals(selectedOption)) {
                        //匹配则加 1
                        curOptionAnalyzeStandardDTO.setSelectedCount(curOptionAnalyzeStandardDTO.getSelectedCount() + 1);
                    }
                }
            }
            //更新信息
            questionOptionMap.put(answerSelectedDTO.getQuestionId(), curQesIdStandardOptionList);
        }

        //统计答卷中，对当前问卷的解答信息
        return organizeAnalysisResult(questionAnalyzeDTOList, questionOptionMap);
    }

    private List<StatisticalAnalysisResultVO> organizeAnalysisResult(List<QuestionAnalyzeDTO> questionAnalyzeDTOList,
                                                                     Map<Long, List<QuestionOptionAnalyzeDTO>> questionOptionMap) throws Exception {
        List<StatisticalAnalysisResultVO> analzeResultList = new ArrayList<>();
        StatisticalAnalysisResultVO resultItem = null;
        List<String> optionList = null;
        List<Integer> valueList = null;

        List<QuestionOptionAnalyzeDTO> questionOptionAnalyzeDTOS = null;
        for (QuestionAnalyzeDTO questionAnalyzeDTO : questionAnalyzeDTOList) {
            //map中不包含当前问题id的答项信息
            if (!questionOptionMap.containsKey(questionAnalyzeDTO.getQuestionId())) {
                continue;
            }
            questionOptionAnalyzeDTOS = questionOptionMap.get(questionAnalyzeDTO.getQuestionId());
            if (questionOptionAnalyzeDTOS.size() <= 0) {
                continue;
            }
            optionList = new ArrayList<>();
            valueList = new ArrayList<>();
            resultItem = new StatisticalAnalysisResultVO();
            for (QuestionOptionAnalyzeDTO questionOptionAnalyzeDTO : questionOptionAnalyzeDTOS) {
                optionList.add(questionOptionAnalyzeDTO.getOptionContent());
                valueList.add(questionOptionAnalyzeDTO.getSelectedCount());
            }
            resultItem.setValueList(valueList);
            resultItem.setOptionList(optionList);
            resultItem.setQuestionId(questionAnalyzeDTO.getQuestionId());
            resultItem.setQuestionContent(questionAnalyzeDTO.getQuestionContent());
            resultItem.setQuestionType(questionAnalyzeDTO.getQuestionType());
            resultItem.setQuestionOrder(questionAnalyzeDTO.getQuestionOrder());
            analzeResultList.add(resultItem);
        }
        return analzeResultList;
    }

    /**
     * 查询调查问卷全部单选题、多选题信息
     *
     * @param missionPaperId
     * @return
     * @throws Exception
     */
    private QesQuestionInfoDTO getQuestionAnalyzeDTOList(String missionPaperId) throws Exception {
        //查询原始问卷中的全部问题信息，准备好容器
        List<QuestionAnalyzeDO> questionAnalyzeDOS = statisticalAnalysisMapper.listQuestionAnalyzeDOByQesPaperId(
                missionPaperId,
                QuestionTypeEnum.SINGLE_CHOICE.getCode(),
                QuestionTypeEnum.MULTIPLE_CHOICE.getCode(),
                QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getCode(),
                QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getCode());
        if (questionAnalyzeDOS.size() <= 0) {
            return null;
        }

        List<QuestionAnalyzeDTO> questionAnalyzeDTOS = new ArrayList<>();
        QuestionAnalyzeDTO questionAnalyzeDTO = null;
        List<QuestionOptionAnalyzeDTO> questionOptionAnalyzeDTOS = null;

        Map<Long, List<QuestionOptionAnalyzeDTO>> questionMap = new HashMap<>();

        for (QuestionAnalyzeDO analyzeDO : questionAnalyzeDOS) {
            questionAnalyzeDTO = new QuestionAnalyzeDTO();
            questionOptionAnalyzeDTOS =
                    StatisticalAnalysisVODOUtil.toOptionsItem(analyzeDO.getOptionStr(), analyzeDO.getQuestionTypeCode());
            if (questionAnalyzeDOS.size() <= 0) {
                continue;
            }
            //题目id--题目选项信息 Map
            questionMap.put(analyzeDO.getQuestionId(), questionOptionAnalyzeDTOS);
            //问题选项信息
            questionAnalyzeDTO.setQuestionOptionAnalyzeDTOS(questionOptionAnalyzeDTOS);
            //问题id
            questionAnalyzeDTO.setQuestionId(analyzeDO.getQuestionId());
            //问题内容
            questionAnalyzeDTO.setQuestionContent(analyzeDO.getQuestionContent());
            //问题在原问卷中序号
            questionAnalyzeDTO.setQuestionOrder(analyzeDO.getQuestionOrder());
            //问题类型 此处将数据库中存储代码转化为视图中可以理解的字符串
            questionAnalyzeDTO.setQuestionType(QesManageVODOUtil.parse2VOQuestionType(analyzeDO.getQuestionTypeCode()));
            questionAnalyzeDTOS.add(questionAnalyzeDTO);
        }
        QesQuestionInfoDTO qesQuestionInfoDTO = new QesQuestionInfoDTO();
        qesQuestionInfoDTO.setQuestionOptionMap(questionMap);
        qesQuestionInfoDTO.setQuestionAnalyzeDTOS(questionAnalyzeDTOS);
        return qesQuestionInfoDTO;
    }

    /**
     * 查询答案信息
     *
     * @param missionId      调查任务id
     * @param missionPaperId 调查问卷ID
     * @return
     * @throws Exception
     */
    private List<AnswerInfoDealedDTO> getAnswerInfoDOList(String missionId, String missionPaperId) throws Exception {
        List<AnswerInfoDO> answerInfoDOList = statisticalAnalysisMapper.listAnswerInfoDOByQesPaperIdMissionId(
                missionPaperId,
                missionId,
                QuestionTypeEnum.SINGLE_CHOICE.getCode(),
                QuestionTypeEnum.MULTIPLE_CHOICE.getCode(),
                QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getCode(),
                QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getCode());
        if (answerInfoDOList.size() <= 0) {
            return null;
        }
        List<AnswerInfoDealedDTO> answerInfoDealedDTOList = new ArrayList<>();
        AnswerInfoDealedDTO answerInfoDealedDTO = null;
        List<String> answerOptionList = null;
        for (AnswerInfoDO answerInfoDO : answerInfoDOList) {
            if (answerInfoDO.getAnswerStr().equals(CONSTANT.getNullAnswerString())) {
                continue;
            }
            answerOptionList = Arrays.asList(answerInfoDO.getAnswerStr().split("\\|\\|"));
            answerInfoDealedDTO = new AnswerInfoDealedDTO();
            answerInfoDealedDTO.setQuestionId(answerInfoDO.getQuestionId());
            answerInfoDealedDTO.setAnswerOptions(answerOptionList);
            answerInfoDealedDTOList.add(answerInfoDealedDTO);
        }
        return answerInfoDealedDTOList;
    }

    private StatisticalAnalysisMapper statisticalAnalysisMapper;

    @Autowired
    public StatisticalAnalysisServiceImpl(StatisticalAnalysisMapper statisticalAnalysisMapper) {
        this.statisticalAnalysisMapper = statisticalAnalysisMapper;
    }
}
