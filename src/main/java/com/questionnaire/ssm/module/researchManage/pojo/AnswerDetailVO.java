package com.questionnaire.ssm.module.researchManage.pojo;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/23.
 * Description: 答卷详细信息
 */
public class AnswerDetailVO {
    //题目ID
    private Long questionId;
    //题目类型
    private String questionType;
    //答案信息 注意index有意义
    private List<String> answer;

    @Override
    public String toString() {
        return "AnswerDetailVO{" +
                "questionId=" + questionId +
                ", questionType='" + questionType + '\'' +
                ", answer=" + answer +
                '}';
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }
}
