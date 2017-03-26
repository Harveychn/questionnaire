package com.questionnaire.ssm.module.global.enums;

/**
 * Created by 郑晓辉 on 2017/3/25.
 * Description: 用户无效情况枚举
 */
public enum UserValidaEnum {
    UNKNOWN_ERROR(-1, "未知错误！"),
    VALIDA_OK(0, "校验成功"),
    NOT_LOGIN(1, "用户未登录"),
    NO_ROLE(2, "用户不具备角色"),
    NO_PERMISSION(3, "用户无相应权限"),
    OLD_PASSWORD_ERROR(4,"旧密码错误")
    ;
    private int code;
    private String message;

    UserValidaEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
