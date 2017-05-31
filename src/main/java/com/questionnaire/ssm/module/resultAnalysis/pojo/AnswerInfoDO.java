package com.questionnaire.ssm.module.resultAnalysis.pojo;

/**
 * Created by 郑晓辉 on 2017/5/30.
 * Description: 答案信息
 */
public class AnswerInfoDO {
    //问题id
    private Long questionId;
    //答案文本
    private String answerStr;

    @Override
    public String toString() {
        return "AnswerInfoDO{" +
                "questionId=" + questionId +
                ", answerStr='" + answerStr + '\'' +
                '}';
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswerStr() {
        return answerStr;
    }

    public void setAnswerStr(String answerStr) {
        this.answerStr = answerStr;
    }
}
