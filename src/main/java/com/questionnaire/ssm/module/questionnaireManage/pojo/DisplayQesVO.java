package com.questionnaire.ssm.module.questionnaireManage.pojo;


import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/27.
 * Description: 查看问卷视图实体
 */
public class DisplayQesVO {
    /*问卷信息*/
    private Long questionnaireId;//问卷id 创建时默认为0
    private String questionnaireTitle;//问卷标题 (必填字段，不得为空)
    private String questionnaireSubtitle;//问卷副标题
    private String questionnaireDescription;//问卷描述

    /*问卷题目信息*/
    private List<QuestionVO> questions;

    @Override
    public String toString() {
        return "DisplayQesVO{" +
                "questionnaireId=" + questionnaireId +
                ", questionnaireTitle='" + questionnaireTitle + '\'' +
                ", questionnaireSubtitle='" + questionnaireSubtitle + '\'' +
                ", questionnaireDescription='" + questionnaireDescription + '\'' +
                ", questions=" + questions +
                '}';
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestionnaireTitle() {
        return questionnaireTitle;
    }

    public void setQuestionnaireTitle(String questionnaireTitle) {
        this.questionnaireTitle = questionnaireTitle;
    }

    public String getQuestionnaireSubtitle() {
        return questionnaireSubtitle;
    }

    public void setQuestionnaireSubtitle(String questionnaireSubtitle) {
        this.questionnaireSubtitle = questionnaireSubtitle;
    }

    public String getQuestionnaireDescription() {
        return questionnaireDescription;
    }

    public void setQuestionnaireDescription(String questionnaireDescription) {
        this.questionnaireDescription = questionnaireDescription;
    }

    public List<QuestionVO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionVO> questions) {
        this.questions = questions;
    }
}
