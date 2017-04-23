package com.questionnaire.ssm.module.researchManage.pojo;

/**
 * Created by 郑晓辉 on 2017/4/23.
 * Description: 调查任务问卷信息
 */
public class ResearchQesPaperVO {
    //问卷id
    private Long questionnaireId;
    //问卷标题
    private String questionnaireTitle;
    //问卷副标题
    private String questionnaireSubtitle;
    //问卷描述
    private String questionnaireDescription;
    //最少提交量
    private Long minSubmit;

    @Override
    public String toString() {
        return "ResearchQesPaperVO{" +
                "questionnaireId=" + questionnaireId +
                ", questionnaireTitle='" + questionnaireTitle + '\'' +
                ", questionnaireSubtitle='" + questionnaireSubtitle + '\'' +
                ", questionnaireDescription='" + questionnaireDescription + '\'' +
                ", minSubmit=" + minSubmit +
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

    public Long getMinSubmit() {
        return minSubmit;
    }

    public void setMinSubmit(Long minSubmit) {
        this.minSubmit = minSubmit;
    }
}
