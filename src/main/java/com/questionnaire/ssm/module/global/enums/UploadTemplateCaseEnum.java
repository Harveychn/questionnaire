package com.questionnaire.ssm.module.global.enums;

/**
 * Created by 郑晓辉 on 2017/4/16.
 * Description: 上传文件规定的模板样式
 */
public enum UploadTemplateCaseEnum {
    UNIT_TEMPLATE,//单位信息表
    USER_INFO_TEMPLATE,//用户信息表
    UNKNOWN_TEMPLATE;//未知模板类型

    private int code;

    public int getCode() {
        return code;
    }
}
