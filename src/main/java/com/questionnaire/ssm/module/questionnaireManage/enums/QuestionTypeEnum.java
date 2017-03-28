package com.questionnaire.ssm.module.questionnaireManage.enums;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:问卷类型枚举
 */
public enum QuestionTypeEnum {
    UNKNOWN_TYPE("0", "未知题型", ""),
    SINGLE_CHOICE("1", "单选题", "||"),//单选题
    MULTIPLE_CHOICE("2", "多选题", "||"),//多选题
    DROP_SELECTION("3", "下拉选择题", "||"),//下拉选择题
    SINGLE_LINE_BLANK("4", "单项填空题", "||"),//单项填空题
    MULTI_LINE_BLANK("5", "多项填空题", "||"),//多项填空题
    PICTURE_SELECTION("6", "图片选择题", "||"),//图片选择题
    ;

    QuestionTypeEnum(String code, String questionType, String divideStr) {
        this.code = code;
        this.questionType = questionType;
        this.divideStr = divideStr;
    }

    /*题目类型编码*/
    private String code;
    /*题目类型*/
    private String questionType;
    /*切割选项字符串*/
    private String divideStr;

    public String getCode() {
        return code;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getDivideStr() {
        return divideStr;
    }
}
