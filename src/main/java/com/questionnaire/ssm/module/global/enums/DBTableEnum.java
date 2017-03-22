package com.questionnaire.ssm.module.global.enums;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:数据库中有的数据表格枚举
 */
public enum DBTableEnum {
    ANSWER(1, "answer"),
    ANSWER_PAPER(2, "answer_paper"),
    MAPPING_ANSWER_PAPER_QUESTION(3, "mapping_answer_paper_question"),
    MAPPING_MISSION_QUESTIONNAIRE(4, "mapping_answer_mission_questionnaire"),
    MAPPING_MISSION_ROLE(5, "mapping_mission_role"),
    MAPPING_MISSION_UNIT(6, "mapping_mission_unit"),
    MAPPING_NOTICE_ROLE(7, "mapping_notice_role"),
    MAPPING_NOTICE_UNIT(8, "mapping_notice_unit"),
    MAPPING_QUESTIONNAIRE_QUESTION(9, "mapping_questionnaire_question"),
    MAPPING_ROLE_PERMISSION(10, "mapping_role_permission"),
    NOTICE(11, "notice"),
    PERMISSION(12, "permission"),
    QUESTION(13, "question"),
    QUESTION_OPTION(14, "question_option"),
    QUESTIONNAIRE(15, "questionnaire"),
    RECORD_LOGIN(16, "record_login"),
    RECORD_OPERATE_ANSWER_PAPER(17, "record_operate_answer_paper"),
    RECORD_OPERATE_MISSION(18, "record_operate_mission"),
    RECORD_OPERATE_NOTICE(19, "record_operate_notice"),
    RECORD_OPERATE_QUESTIONNAIRE(20, "record_operate_questionnaire"),
    RESEARCH_MISSION(21, "research_mission"),
    ROLE(22, "role"),
    UNIT(23, "unit"),
    USER(24, "user"),;
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
