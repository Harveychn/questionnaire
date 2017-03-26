package com.questionnaire.ssm.module.global.enums;

/**
 * Created by 郑晓辉 on 2017/3/26.
 * Description: 用户操作记录的动作枚举
 */
public enum UserActionEnum {
    INSERT_ACTION(1, "增加动作"),
    DELETE_ACTION(2, "删除动作"),
    UPDATE_ACTION(3, "更新动作"),
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
