package com.questionnaire.ssm.module.resultAnalysis.pojo;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/5/30.
 * Description: 原始调查问卷信息
 */
public class QesPaperAnalyzeDTO {
    //调查问卷id
    private Long qesPaperId;
    //调查问卷的问题信息
    private List<QuestionAnalyzeDTO> questionAnalyzeDTOS;

    @Override
    public String toString() {
        return "QesPaperAnalyzeDTO{" +
                "qesPaperId=" + qesPaperId +
                ", questionAnalyzeDTOS=" + questionAnalyzeDTOS +
                '}';
    }

    public Long getQesPaperId() {
        return qesPaperId;
    }

    public void setQesPaperId(Long qesPaperId) {
        this.qesPaperId = qesPaperId;
    }

    public List<QuestionAnalyzeDTO> getQuestionAnalyzeDTOS() {
        return questionAnalyzeDTOS;
    }

    public void setQuestionAnalyzeDTOS(List<QuestionAnalyzeDTO> questionAnalyzeDTOS) {
        this.questionAnalyzeDTOS = questionAnalyzeDTOS;
    }
}
