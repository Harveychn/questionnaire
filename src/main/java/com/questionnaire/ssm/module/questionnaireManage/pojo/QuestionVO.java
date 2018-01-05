package com.questionnaire.ssm.module.questionnaireManage.pojo;

import com.questionnaire.ssm.module.global.constant.CONSTANT;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:问卷中问题数据
 */
public class QuestionVO {
    /**
     * 题目信息
     */
    private Long questionId;//问题id
    private String questionContext;//题目
    private String questionType;//题目类型
    private String questionDescription;//题目描述
    private Boolean isMust;//是否必做题

    //2017-12-5添加字段  因考虑到数组下标从0开始，此处设置默认值为-1
    private int questionFollow = CONSTANT.NO_FOLLOW_DEFAULT_VALUE;//题目转到第几题

    /**
     * 题目选项信息
     */
    private List<QuestionOptionVO> options;//题目选项信息

    @Override
    public String toString() {
        return "QuestionVO{" +
                "questionId=" + questionId +
                ", questionContext='" + questionContext + '\'' +
                ", questionType='" + questionType + '\'' +
                ", questionDescription='" + questionDescription + '\'' +
                ", isMust=" + isMust +
                ", questionFollow='" + questionFollow + '\'' +
                ", options=" + options +
                '}';
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContext() {
        return questionContext;
    }

    public void setQuestionContext(String questionContext) {
        this.questionContext = questionContext;
    }

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

    public int getQuestionFollow() {
        return questionFollow;
    }

    public void setQuestionFollow(int questionFollow) {
        this.questionFollow = questionFollow;
    }

    public List<QuestionOptionVO> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOptionVO> options) {
        this.options = options;
    }
}
