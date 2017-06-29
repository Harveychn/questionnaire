package com.questionnaire.ssm.module.researchManage.enums;

/**
 * Created by 郑晓辉 on 2017/5/29.
 * Description: 调查任务状态枚举
 */
public enum MissionStatusEnum {
    UNRELEASED_STATUS,//指当前时间小于发布时间
    RELEASED_STATUS,//指当前时间大于发布时间
    GOING_STATUS,//指当前时间大于开始时间小于截止时间
    FINISH_STATUS,//指当前时间大于截止时间
    UNFINISHED_STATUS,//指当前时间小于截止时间
    CREATED_STATUS//存在任务信息
}
