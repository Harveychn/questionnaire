package com.questionnaire.ssm.module.global.enums;

/**
 * Created by 郑晓辉 on 2017/3/25.
 * Description: 系统中的所有权限信息枚举
 * <p>
 * 本枚举需要数据库中与 permission 表的记录相同
 */
public enum PermissionEnum {
    CREATE_QUESTIONNAIRE("创建问卷"),
    PUBLISH_QUESTIONNAIRE("发布问卷"),
    ADD_INVESTIGATOR("新建调查员"),
    ADD_BUSINESS_ADMIN("新建疾控中心管理员"),
    CREATE_NOTICE("新建公告"),
    DELETE_NOTICE("删除公告"),
    DELETE_INVESTIGATOR("删除公告"),
    DELETE_BUSINESS_ADMIN("删除疾控中心管理员"),
    CHANGE_PASSWORD("更改密码");
    private String permission;

    PermissionEnum(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
