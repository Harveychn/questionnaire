package com.questionnaire.ssm.module.questionnaireManage.pojo;

import com.questionnaire.ssm.module.global.constant.CONSTANT;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:题目选项信息
 */
public class QuestionOptionVO {
    private int optionOrder;//选项顺序
    private String option;//选项

    //    2017-12-5新增字段
    private int optionFollow = CONSTANT.NO_FOLLOW_DEFAULT_VALUE;//选项后跟

    public int getOptionFollow() {
        return optionFollow;
    }

    public void setOptionFollow(int optionFollow) {
        this.optionFollow = optionFollow <= CONSTANT.NO_FOLLOW_DEFAULT_VALUE ? CONSTANT.NO_FOLLOW_DEFAULT_VALUE : optionFollow;
    }

    @Override
    public String toString() {
        return "QuestionOptionVO{" +
                "optionOrder=" + optionOrder +
                ", option='" + option + '\'' +
                ", optionFollow='" + optionFollow + '\'' +
                '}';
    }

    public int getOptionOrder() {
        return optionOrder;
    }

    public void setOptionOrder(int optionOrder) {
        this.optionOrder = optionOrder;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

}
