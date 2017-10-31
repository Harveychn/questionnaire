package com.questionnaire.ssm.module.resultAnalysis.pojo;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/5/30.
 * Description: 原始调查问卷问题信息
 */
public class QuestionAnalyzeDTO {
    //问题编号
    private Long questionId;
    //问题在问卷中的题目序号
    private int questionOrder;
    //问题内容
    private String questionContent;
    //问题类型
    private String questionType;
    //问题原始选项信息
    private List<QuestionOptionAnalyzeDTO> questionOptionAnalyzeDTOS;

    @Override
    public String toString() {
        return "QuestionAnalyzeDTO{" +
                "questionId=" + questionId +
                ", questionOrder=" + questionOrder +
                ", questionContent='" + questionContent + '\'' +
                ", questionType='" + questionType + '\'' +
                ", questionOptionAnalyzeDTOS=" + questionOptionAnalyzeDTOS +
                '}';
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public int getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(int questionOrder) {
        this.questionOrder = questionOrder;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public List<QuestionOptionAnalyzeDTO> getQuestionOptionAnalyzeDTOS() {
        return questionOptionAnalyzeDTOS;
    }

    public void setQuestionOptionAnalyzeDTOS(List<QuestionOptionAnalyzeDTO> questionOptionAnalyzeDTOS) {
        this.questionOptionAnalyzeDTOS = questionOptionAnalyzeDTOS;
    }
}
