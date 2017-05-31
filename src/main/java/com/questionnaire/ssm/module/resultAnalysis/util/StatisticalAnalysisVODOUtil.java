package com.questionnaire.ssm.module.resultAnalysis.util;

import com.questionnaire.ssm.module.questionnaireManage.enums.QuestionTypeEnum;
import com.questionnaire.ssm.module.questionnaireManage.pojo.QuestionOptionVO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.QuestionAnalyzeDO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.QuestionAnalyzeDTO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.QuestionOptionAnalyzeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/5/30.
 * Description:
 */
public class StatisticalAnalysisVODOUtil {

    /**
     * 将数据库选项字符串切割成正常的选项信息
     *
     * @param optionString
     * @return
     * @throws Exception
     */
    public static List<QuestionOptionAnalyzeDTO> toOptionsItem(String optionString, String questionTypeCode) throws Exception {
        String[] options = null;
        if (questionTypeCode.equals(QuestionTypeEnum.SINGLE_CHOICE.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.MULTIPLE_CHOICE.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.SINGLE_LINE_BLANK.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.MULTI_LINE_BLANK.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.DROP_SELECTION.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.SHORT_ANSWER.getCode())) {
            options = optionString.split("\\|\\|");
        }
        if (options == null) {
            return null;
        }

        List<QuestionOptionAnalyzeDTO> questionOptionAnalyzeDTOS = new ArrayList<>();
        QuestionOptionAnalyzeDTO questionOptionAnalyzeDTO = null;
        for (int order = 0; order < options.length; order++) {
            questionOptionAnalyzeDTO = new QuestionOptionAnalyzeDTO();
            questionOptionAnalyzeDTO.setOptionOrder(order);
            questionOptionAnalyzeDTO.setOptionContent(options[order]);
            questionOptionAnalyzeDTOS.add(order, questionOptionAnalyzeDTO);
        }
        return questionOptionAnalyzeDTOS;
    }
}
