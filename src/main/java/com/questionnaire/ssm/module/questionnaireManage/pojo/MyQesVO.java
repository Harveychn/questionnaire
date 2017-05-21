package com.questionnaire.ssm.module.questionnaireManage.pojo;

/**
 * Created by 郑晓辉 on 2017/3/27.
 * Description: 用户创建的问卷信息视图实体
 */
public class MyQesVO {
    /*问卷id*/
    private long questionnaireId;
    /*问卷标题*/
    private String questionnaireTitle;
    /*问卷副标题*/
    private String questionnaireSubtitle;
    /*问卷描述*/
    private String questionnaireDescription;
    /*问卷创建时间*/
    private String questionnaireCreateDate;
    /*问卷是否模板*/
    private boolean isTemplate;
    /*问卷已经共享*/
    private boolean isShare;
    /*问卷是否已经完成*/
    private boolean isDone;

    @Override
    public String toString() {
        return "MyQesVO{" +
                "questionnaireId=" + questionnaireId +
                ", questionnaireTitle='" + questionnaireTitle + '\'' +
                ", questionnaireSubtitle='" + questionnaireSubtitle + '\'' +
                ", questionnaireDescription='" + questionnaireDescription + '\'' +
                ", questionnaireCreateDate='" + questionnaireCreateDate + '\'' +
                ", isTemplate=" + isTemplate +
                ", isShare=" + isShare +
                ", isDone=" + isDone +
                '}';
    }

    public long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(long questionnaireId) {
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

    public String getQuestionnaireCreateDate() {
        return questionnaireCreateDate;
    }

    public void setQuestionnaireCreateDate(String questionnaireCreateDate) {
        this.questionnaireCreateDate = questionnaireCreateDate;
    }

    public boolean isTemplate() {
        return isTemplate;
    }

    public void setTemplate(boolean template) {
        isTemplate = template;
    }

    public boolean isShare() {
        return isShare;
    }

    public void setShare(boolean share) {
        isShare = share;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
