package com.questionnaire.ssm.module.global.enums;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:数据库中有的数据表格枚举
 */
public enum DBTableEnum {
    ANSWER_DETAIL(1, "answer_detail"),
    ANSWER_PAPER(2, "answer_paper"),
    MAPPING_MISSION_QUESTIONNAIRE(3, "mapping_answer_mission_questionnaire"),
    MAPPING_QUESTIONNAIRE_QUESTION(4, "mapping_questionnaire_question"),
    MAPPING_ROLE_PERMISSION(5, "mapping_role_permission"),
    MISSION(6,"mission"),
    NOTICE(7, "notice"),
    PERMISSION(8, "permission"),
    QUESTION(9, "question"),
    QUESTIONNAIRE(10, "questionnaire"),
    ROLE(11, "role"),
    UNIT(12, "unit"),
    USER(13, "user"),;
    /*表格编号*/
    private int code;
    /*表格名称*/
    private String tableName;

    DBTableEnum(int code, String tableName) {
        this.code = code;
        this.tableName = tableName;
    }

    public int getCode() {
        return code;
    }

    public String getTableName() {
        return tableName;
    }
}
