package com.questionnaire.ssm.module.resultAnalysis.pojo;

import java.util.List;
import java.util.Map;

/**
 * Created by 郑晓辉 on 2017/5/30.
 * Description: 调查问卷的问题信息
 */
public class QesQuestionInfoDTO {
    //问题信息
    private List<QuestionAnalyzeDTO> questionAnalyzeDTOS;
    //问题-选项信息
    private Map<Long, List<QuestionOptionAnalyzeDTO>> questionOptionMap;

    @Override
    public String toString() {
        return "QesQuestionInfoDTO{" +
                "questionAnalyzeDTOS=" + questionAnalyzeDTOS +
                ", questionOptionMap=" + questionOptionMap +
                '}';
    }

    public List<QuestionAnalyzeDTO> getQuestionAnalyzeDTOS() {
        return questionAnalyzeDTOS;
    }

    public void setQuestionAnalyzeDTOS(List<QuestionAnalyzeDTO> questionAnalyzeDTOS) {
        this.questionAnalyzeDTOS = questionAnalyzeDTOS;
    }

    public Map<Long, List<QuestionOptionAnalyzeDTO>> getQuestionOptionMap() {
        return questionOptionMap;
    }

    public void setQuestionOptionMap(Map<Long, List<QuestionOptionAnalyzeDTO>> questionOptionMap) {
        this.questionOptionMap = questionOptionMap;
    }
}
