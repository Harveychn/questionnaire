package com.questionnaire.ssm.module.questionnaireManage.pojo;

/**
 * Created by 郑晓辉 on 2017/4/24.
 * Description: 回收站问卷视图
 */
public class TempDelQesPaperVO {
    //问卷ID
    private Long questionnaireId;
    //问卷标题
    private String questionnaireTitle;
    //问卷副标题
    private String questionnaireSubtitle;
    //问卷描述
    private String questionnaireDescription;
    //不可见问卷来源
    private String originSource;

    @Override
    public String toString() {
        return "TempDelQesPaperVO{" +
                "questionnaireId=" + questionnaireId +
                ", questionnaireTitle='" + questionnaireTitle + '\'' +
                ", questionnaireSubtitle='" + questionnaireSubtitle + '\'' +
                ", questionnaireDescription='" + questionnaireDescription + '\'' +
                ", originSource='" + originSource + '\'' +
                '}';
    }

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

    public String getQuestionnaireDescription() {
        return questionnaireDescription;
    }

    public void setQuestionnaireDescription(String questionnaireDescription) {
        this.questionnaireDescription = questionnaireDescription;
    }

    public String getOriginSource() {
        return originSource;
    }

    public void setOriginSource(String originSource) {
        this.originSource = originSource;
    }
}
