package com.questionnaire.ssm.module.questionnaireManage.pojo;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:提取视图中的 题目、题目选项、问卷-题目映射关系
 */
public class QuestionDTO {
    private List<Question> question;
    private List<QuestionOption> questionOption;

    public QuestionDTO() {

    }

    public QuestionDTO(List<Question> question, List<QuestionOption> questionOption) {
        this.question = question;
        this.questionOption = questionOption;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    public List<QuestionOption> getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(List<QuestionOption> questionOption) {
        this.questionOption = questionOption;
    }
}
