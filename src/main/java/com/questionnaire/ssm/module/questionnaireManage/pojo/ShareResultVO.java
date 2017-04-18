package com.questionnaire.ssm.module.questionnaireManage.pojo;

/**
 * Created by 郑晓辉 on 2017/4/7.
 * Description: 用户共享问卷结果
 */
public class ShareResultVO {
    /*之前分享过的问卷数*/
    private int sharedFail;
    /*成功共享的问卷数*/
    private int shareOK;

    public ShareResultVO(int sharedFail, int shareOK) {
        this.sharedFail = sharedFail;
        this.shareOK = shareOK;
    }

    @Override
    public String toString() {
        return "ShareResultVO{" +
                "sharedFail=" + sharedFail +
                ", shareOK=" + shareOK +
                '}';
    }

    public int getSharedFail() {
        return sharedFail;
    }

    public void setSharedFail(int sharedFail) {
        this.sharedFail = sharedFail;
    }

    public int getShareOK() {
        return shareOK;
    }

    public void setShareOK(int shareOK) {
        this.shareOK = shareOK;
    }
}
