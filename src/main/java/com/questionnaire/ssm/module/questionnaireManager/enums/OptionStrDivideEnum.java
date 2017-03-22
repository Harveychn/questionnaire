package com.questionnaire.ssm.module.questionnaireManager.enums;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:选项字符串区分符枚举
 */
public enum OptionStrDivideEnum {
    DIVIDE_SYMBOL("||"),;
    /*切分字符串*/
    private String divider;

    OptionStrDivideEnum(String divider) {
        this.divider = divider;
    }

    public String getDivider() {
        return divider;
    }
}
