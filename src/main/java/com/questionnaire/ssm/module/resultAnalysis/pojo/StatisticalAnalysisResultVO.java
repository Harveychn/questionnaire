package com.questionnaire.ssm.module.resultAnalysis.pojo;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/5/30.
 * Description: 统计分析结果数据
 */
public class StatisticalAnalysisResultVO {
    //问题编号
    private Long questionId;
    //问题问卷中序号
    private int questionOrder;
    //问题内容
    private String questionContent;
    //问题类型
    private String questionType;
    //选项list
    private List<String> optionList;
    //选项选中值list
    private List<Integer> valueList;

    @Override
    public String toString() {
        return "StatisticalAnalysisResultVO{" +
                "questionId=" + questionId +
                ", questionOrder=" + questionOrder +
                ", questionContent='" + questionContent + '\'' +
                ", questionType='" + questionType + '\'' +
                ", optionList=" + optionList +
                ", valueList=" + valueList +
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

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public List<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<String> optionList) {
        this.optionList = optionList;
    }

    public List<Integer> getValueList() {
        return valueList;
    }

    public void setValueList(List<Integer> valueList) {
        this.valueList = valueList;
    }
}
