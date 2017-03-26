package com.questionnaire.ssm.module.global.enums;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:操作数据库结果枚举
 */
public enum OperateDBEnum {
    DELETE_FAIL(-4, "删除失败！"),
    SELECT_FAIL(-3, "查询失败！"),
    INSERT_FAIL(-2, "插入失败！"),
    UPDATE_FAIL(-1, "更新失败！"),
    UPDATE_SUCCESS(1, "更新成功！"),
    INSERT_SUCCESS(2, "插入成功！"),
    SELECT_SUCCESS(3, "查询成功！"),
    DELETE_SUCCESS(4, "删除成功！"),
    UNKNOWN_ERROR(0, "未知错误！")
    ;

    /*异常编号*/
    private int code;
    /*异常信息*/
    private String message;

    OperateDBEnum(int code, String message) {
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
