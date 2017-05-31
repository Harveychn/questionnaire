package com.questionnaire.ssm.module.resultAnalysis.pojo;

/**
 * Created by 郑晓辉 on 2017/5/30.
 * Description: 原始调查问卷问题信息 数据库查询出来的实体信息类
 */
public class QuestionAnalyzeDO {
    //问题id
    private Long questionId;
    //问题原始序号
    private int questionOrder;
    //问题内容
    private String questionContent;
    //问题答项字符串
    private String optionStr;
    //问题类型数据库代码
    private String questionTypeCode;

    @Override
    public String toString() {
        return "QuestionAnalyzeDO{" +
                "questionId=" + questionId +
                ", questionOrder=" + questionOrder +
                ", questionContent='" + questionContent + '\'' +
                ", optionStr='" + optionStr + '\'' +
                ", questionTypeCode='" + questionTypeCode + '\'' +
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

    public String getOptionStr() {
        return optionStr;
    }

    public void setOptionStr(String optionStr) {
        this.optionStr = optionStr;
    }

    public String getQuestionTypeCode() {
        return questionTypeCode;
    }

    public void setQuestionTypeCode(String questionTypeCode) {
        this.questionTypeCode = questionTypeCode;
    }
}
