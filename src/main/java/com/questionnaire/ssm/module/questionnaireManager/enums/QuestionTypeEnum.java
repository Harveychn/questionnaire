package com.questionnaire.ssm.module.questionnaireManager.enums;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:问卷类型枚举
 */
public enum QuestionTypeEnum {
    SINGLE_CHOICE("1"),//单选题
    MULTIPLE_CHOICE("2"),//多选题

    ;

    QuestionTypeEnum(String code) {
        this.code = code;
    }

    /*题目类型编码*/
    private String code;

    public String getCode() {
        return code;
    }
}
