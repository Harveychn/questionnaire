package com.questionnaire.ssm.module.questionnaireManager.exception;

import com.questionnaire.ssm.module.global.enums.OperateDBEnum;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:插入数据库异常
 */
public class InsertException extends RuntimeException {
    /*错误编号*/
    private int code;
    /*错误表格*/
    private String tableNme;

    public InsertException(OperateDBEnum operateDBEnum, String tableNme) {
        super(operateDBEnum.getMessage());
        this.code = operateDBEnum.getCode();
        this.tableNme = tableNme;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTableNme() {
        return tableNme;
    }

    public void setTableNme(String tableNme) {
        this.tableNme = tableNme;
    }
}
