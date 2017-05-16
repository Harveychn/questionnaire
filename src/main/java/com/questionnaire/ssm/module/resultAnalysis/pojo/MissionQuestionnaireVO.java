package com.questionnaire.ssm.module.resultAnalysis.pojo;

/**
 * Created by 95884 on 2017/5/14.
 */
public class MissionQuestionnaireVO {
    /*问卷id*/
    private Long questionnaireId;
    /*任务id*/
    private Long missionId;

    @Override
    public String toString() {
        return "MissionQuestionnaireVO{" +
                "questionnaireId=" + questionnaireId +
                ", missionId=" + missionId +
                '}';
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }


    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

}
