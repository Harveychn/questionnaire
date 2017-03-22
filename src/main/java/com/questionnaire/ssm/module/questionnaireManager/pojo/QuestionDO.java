package com.questionnaire.ssm.module.questionnaireManager.pojo;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:问卷中问题数据
 */
public class QuestionDO {
    /**
     * 题目信息
     */
    private String questionType;//题目类型
    private String questionDescription;//题目描述
    private Boolean isMust;//是否必做题
    private String questionContext;//题目

    /**
     * 题目选项信息
     */
    private List<QuestionOptionDO> options;//题目选项信息

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public Boolean getMust() {
        return isMust;
    }

    public void setMust(Boolean must) {
        isMust = must;
    }

    public String getQuestionContext() {
        return questionContext;
    }

    public void setQuestionContext(String questionContext) {
        this.questionContext = questionContext;
    }

    public List<QuestionOptionDO> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOptionDO> options) {
        this.options = options;
    }
}
