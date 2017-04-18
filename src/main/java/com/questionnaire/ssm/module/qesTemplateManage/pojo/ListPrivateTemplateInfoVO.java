package com.questionnaire.ssm.module.qesTemplateManage.pojo;


import java.util.Date;

/**
 * Created by 郑晓辉 on 2017/4/6.
 * Description: 个人模板信息
 */
public class ListPrivateTemplateInfoVO {
    /*问卷ID*/
    private Long questionnaireId;
    /*问卷标题*/
    private String questionnaireTitle;
    /*问卷副标题*/
    private String questionnaireSubtitle;
    /*问卷描述*/
    private String questionnaireDescription;
    /*模板加入方式*/
    private String operate_action;
    /*模板添加日期*/
    private Date operate_date;

    @Override
    public String toString() {
        return "ListPrivateTemplateInfoVO{" +
                "questionnaireId=" + questionnaireId +
                ", questionnaireTitle='" + questionnaireTitle + '\'' +
                ", questionnaireSubtitle='" + questionnaireSubtitle + '\'' +
                ", questionnaireDescription='" + questionnaireDescription + '\'' +
                ", operate_action='" + operate_action + '\'' +
                ", operate_date=" + operate_date +
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

    public String getOperate_action() {
        return operate_action;
    }

    public void setOperate_action(String operate_action) {
        this.operate_action = operate_action;
    }

    public Date getOperate_date() {
        return operate_date;
    }

    public void setOperate_date(Date operate_date) {
        this.operate_date = operate_date;
    }
}
