package com.questionnaire.ssm.module.questionnaireManage.enums;

import com.questionnaire.ssm.module.global.enums.RequestResultEnum;

/**
 * Created by 郑晓辉 on 2017/3/24.
 * Description:校验无效结果枚举
 */
public enum CheckInValidEnum {
    QUESTIONNAIRE_TITLE_NULL(RequestResultEnum.ERROR.getCode(),"问卷标题不得为空"),
    ;
    private int code;
    private String message;

    CheckInValidEnum(int code, String message) {
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
