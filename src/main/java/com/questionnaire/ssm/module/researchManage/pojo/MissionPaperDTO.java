package com.questionnaire.ssm.module.researchManage.pojo;

/**
 * Created by 郑晓辉 on 2017/5/24.
 * Description: 任务问卷信息
 */
public class MissionPaperDTO {
    //问卷id
    private Long questionnaireId;
    //问卷标题
    private String questionnaireTitle;
    //问卷描述
    private String questionnaireDescription;
    //最少提交量
    private Long minSubmit;
    //目前提交量
    private Long submitNow;

    @Override
    public String toString() {
        return "MissionPaperDTO{" +
                "questionnaireId=" + questionnaireId +
                ", questionnaireTitle='" + questionnaireTitle + '\'' +
                ", questionnaireDescription='" + questionnaireDescription + '\'' +
                ", minSubmit=" + minSubmit +
                ", submitNow=" + submitNow +
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

    public Long getSubmitNow() {
        return submitNow;
    }

    public void setSubmitNow(Long submitNow) {
        this.submitNow = submitNow;
    }
}
