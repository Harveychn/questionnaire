package com.questionnaire.ssm.module.global.enums;

/**
 * Created by 郑晓辉 on 2017/3/20.
 * Description:操作用户表数据时可能的结果
 */
public enum ModifyUserEnum {
    UPDATE_SUCCESS(0),
    UPDATE_FAIL(1),
    INSERT_SUCCESS(2),
    INSERT_FAIL(3),
    ARGS_NULL(4);

    ModifyUserEnum(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }
}
