package com.questionnaire.ssm.module.questionnaireManager.pojo;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/21.
 * Description:用户创建问卷实体时问卷具体信息
 */
public class CreateQuestionnaireVO {
    /**
     * 问卷基本信息
     */
    private Long questionnaireId;//问卷id 创建时默认为0
    private String questionnaireTitle;//问卷标题 (必填字段，不得为空)
    private String questionnaireSubtitle;//问卷副标题
    private Boolean isTemplate;//是否创建为模板 (必填字段，默认为false)
    private Boolean isDone;//是否编辑完成的问卷 （必填字段，默认为false）
    private String questionnaireDescription;//问卷描述

    /**
     * 问卷题目信息
     */
    private List<QuestionDO> questions;


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

    public Boolean getTemplate() {
        return isTemplate;
    }

    public void setTemplate(Boolean template) {
        isTemplate = template;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public String getQuestionnaireDescription() {
        return questionnaireDescription;
    }

    public void setQuestionnaireDescription(String questionnaireDescription) {
        this.questionnaireDescription = questionnaireDescription;
    }

    public List<QuestionDO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDO> questions) {
        this.questions = questions;
    }
}
