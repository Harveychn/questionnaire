package com.questionnaire.ssm.module.researchManage.pojo;

/**
 * Created by 郑晓辉 on 2017/4/24.
 * Description: 调查任务的问卷信息
 */
public class MissionQesPaperVO {
    //问卷ID
    private Long questionnaireId;
    //当前问卷最少提交量
    private Long minSubmit;

    @Override
    public String toString() {
        return "MissionQesPaperVO{" +
                "questionnaireId=" + questionnaireId +
                ", minSubmit=" + minSubmit +
                '}';
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getMinSubmit() {
        return minSubmit;
    }

    public void setMinSubmit(Long minSubmit) {
        this.minSubmit = minSubmit;
    }
}
