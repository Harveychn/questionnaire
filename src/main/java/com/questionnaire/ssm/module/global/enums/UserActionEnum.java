package com.questionnaire.ssm.module.global.enums;

/**
 * Created by 郑晓辉 on 2017/3/26.
 * Description: 用户操作记录的动作枚举
 */
public enum UserActionEnum {
    /*问卷管理*/
    CREATE_QUESTIONNAIRE(1, "创建问卷"),
    CREATE_QUESTIONNAIRE_AS_TEMPLATE(0, "个人创建为模板"),
    DELETE_TEMPORARY_QUESTIONNAIRE(2, "个人问卷放入回收站"),
    DELETE_FOREVER_QUESTIONNAIRE(3, "永久删除问卷"),
    ADD_2_PRIVATE_TEMPLATE(5, "添加到个人模板库"),
    SHARE_QUESTIONNAIRE_2_PUBLIC_TEMPLATE(7, "分享问卷到公共模板库"),//关联新id（公共模板id）
    ADD_2_PUBLIC_TEMPLATE(8, "添加个人问卷到公共模板库"),//关联原id（个人问卷id）
    COPY_FROM_PUBLIC_TEMPLATE(9,"从公共模板库中复制模板"),//关联新id（个人模板id）
    ADD_PUBLIC_TEMPLATE_2_MY_TEMPLATE_LIBRARY(10, "添加公共模板到个人模板库"),//关联原id（公共模板id）
    CREATE_NOTICE(11,"创建公告"),
    DELETE_NOTICE(12,"删除公告"),
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
