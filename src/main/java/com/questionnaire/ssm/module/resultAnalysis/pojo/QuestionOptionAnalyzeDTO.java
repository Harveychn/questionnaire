package com.questionnaire.ssm.module.resultAnalysis.pojo;

/**
 * Created by 郑晓辉 on 2017/5/30.
 * Description: 原始调查问卷全部选项信息
 */
public class QuestionOptionAnalyzeDTO {
    //选项顺序
    private int optionOrder;
    //选项内容
    private String optionContent;
    //选项在答卷中选中的次数
    private int selectedCount;

    @Override
    public String toString() {
        return "QuestionOptionAnalyzeDTO{" +
                "optionOrder=" + optionOrder +
                ", optionContent='" + optionContent + '\'' +
                ", selectedCount=" + selectedCount +
                '}';
    }

    public int getOptionOrder() {
        return optionOrder;
    }

    public void setOptionOrder(int optionOrder) {
        this.optionOrder = optionOrder;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    public int getSelectedCount() {
        return selectedCount;
    }

    public void setSelectedCount(int selectedCount) {
        this.selectedCount = selectedCount;
    }
}
