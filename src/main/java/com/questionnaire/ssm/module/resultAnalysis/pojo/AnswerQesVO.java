package com.questionnaire.ssm.module.resultAnalysis.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * creator: xiaohui zheng
 * time: 2018/7/14  17:07
 * function: 问卷结果展示界面模型类
 */
public class AnswerQesVO {
    /*答卷信息*/
    private Long answerPaperId;//答卷id
    private Long questionnaireId;//问卷id
    private String questionnaireTitle;//问卷标题
    private String questionnaireSubtitle;//问卷副标题
    private String questionnaireDescription;//问卷描述
    private BigDecimal longitude;//经度
    private BigDecimal latitude;//纬度
    /*答卷题目*/
    private List<QesItem> answerQuestions;


    public static class QesItem {
        private Long questionId;//问题id
        private String questionContext;//题目
        private String questionType;//题目类型
        private String questionDescription;//题目描述
        private Boolean isMust;//是否必做题
        private List<String> answerList;//答案

        public QesItem() {
        }

        public QesItem(Long questionId, String questionContext, String questionType, String questionDescription, Boolean isMust, List<String> answerList) {
            this.questionId = questionId;
            this.questionContext = questionContext;
            this.questionType = questionType;
            this.questionDescription = questionDescription;
            this.isMust = isMust;
            this.answerList = answerList;
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

        public List<String> getAnswerList() {
            return answerList;
        }

        public void setAnswerList(List<String> answerList) {
            this.answerList = answerList;
        }
    }

    public static AnswerQesVO translate(DisplayAnswerPaperVO answerPaperVO) {
        AnswerQesVO result = new AnswerQesVO();
        result.setAnswerPaperId(answerPaperVO.getAnswerPaperId());
        result.setQuestionnaireId(answerPaperVO.getQuestionnaireId());
        result.setQuestionnaireTitle(answerPaperVO.getQuestionnaireTitle());
        result.setQuestionnaireSubtitle(answerPaperVO.getQuestionnaireSubtitle());
        result.setQuestionnaireDescription(answerPaperVO.getQuestionnaireDescription());
        result.setLongitude(answerPaperVO.getLongitude());
        result.setLatitude(answerPaperVO.getLatitude());

        List<AnswerQuestionVO> qesItems = answerPaperVO.getAnswerQuestions();
        if (qesItems != null) {
            List<QesItem> retnItems = new ArrayList<>();
            qesItems.forEach((item) -> {
                QesItem i = new QesItem();
                i.setQuestionId(item.getQuestionId());
                i.setQuestionContext(item.getQuestionContext());
                i.setQuestionType(item.getQuestionType());
                i.setQuestionDescription(item.getQuestionDescription());
                if (item.getAnswerDetail() != null) {
                    String[] answers = item.getAnswerDetail().split("\\|\\|");
                    i.setAnswerList(Arrays.asList(answers));
                }
                retnItems.add(i);
            });
            result.setAnswerQuestions(retnItems);
        }
        return result;
    }

    public AnswerQesVO() {
    }

    public Long getAnswerPaperId() {
        return answerPaperId;
    }

    public void setAnswerPaperId(Long answerPaperId) {
        this.answerPaperId = answerPaperId;
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

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public List<QesItem> getAnswerQuestions() {
        return answerQuestions;
    }

    public void setAnswerQuestions(List<QesItem> answerQuestions) {
        this.answerQuestions = answerQuestions;
    }
}
