package com.questionnaire.ssm.module.global.enums;

/**
 * Created by 郑晓辉 on 2017/3/26.
 * Description: 用户操作记录的动作枚举
 */
public enum UserActionEnum {
    /*问卷管理*/
    CREATE_QUESTIONNAIRE(1, "创建问卷"),
    DELETE_TEMPORARY_QUESTIONNAIRE(2, "问卷放入回收站"),// 设置问卷visible = false
    DELETE_FOREVER_QUESTIONNAIRE(3, "永久删除问卷"),//设置问卷delete = true
    SHARE_QUESTIONNAIRE(4, "共享问卷"),
    ADD_TO_MY_TEMPLATE(5, "添加问卷到我的模板"),

    DELETE_TEMPORARY_MULTI_QUESTIONNAIRE(6, "批量问卷放入回收站"),
    DELETE_FOREVER_MULTI_QUESTIONNAIRE(7, "批量永久删除问卷"),//设置问卷delete = true
    SHARE_MULTI_QUESTIONNAIRE(8, "批量共享问卷"),
    MULTI_ADD_TO_MY_TEMPLATE(9, "批量添加问卷到我的模板"),
    ;

    private int code;
    private String action;

    UserActionEnum(int code, String action) {
        this.code = code;
        this.action = action;
    }

    public int getCode() {
        return code;
    }

    public String getAction() {
        return action;
    }
}
