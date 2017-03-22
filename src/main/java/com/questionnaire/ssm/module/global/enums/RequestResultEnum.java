package com.questionnaire.ssm.module.global.enums;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description: 请求结果枚举
 */
public enum RequestResultEnum {
    SUCCESS(1, "成功！"),
    ERROR(-1, "失败！"),;

    /*结果编码*/
    private int code;
    /*结果信息*/
    private String message;

    RequestResultEnum(int code, String message) {
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
