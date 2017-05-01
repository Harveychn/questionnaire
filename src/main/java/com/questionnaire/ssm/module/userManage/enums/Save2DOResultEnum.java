package com.questionnaire.ssm.module.userManage.enums;

/**
 * Created by 郑晓辉 on 2017/4/21.
 * Description: 上传数据保存到数据库中时会出现结果枚举
 */
public enum Save2DOResultEnum {
    //外键约束
    NO_SUCH_ROLE("角色不存在"),//角色不存在
    NO_SUCH_UNIT("单位不存在"),//单位不存在
    SUCCESS("成功"),//成功插入保存数据
    UNKNOWN_ERROR("未知错误"),//未知错误

    DUPLICATED_USER_RECORD("重复的用户信息"),//重复的用户信息
    DUPLICATED_UNIT_RECORD("重复的单位信息"),

    //校验必要字段
    USER_FORMAT_ERROR("用户信息格式错误"),
    UNIT_FORMAT_ERROR("单位信息格式错误"),
    FORMAT_SUCCESS("格式正确");

    private String message;

    Save2DOResultEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
