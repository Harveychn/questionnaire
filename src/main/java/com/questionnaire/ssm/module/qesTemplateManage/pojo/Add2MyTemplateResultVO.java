package com.questionnaire.ssm.module.qesTemplateManage.pojo;

/**
 * Created by 郑晓辉 on 2017/4/7.
 * Description: 添加公共模板到我的模板库结果
 */
public class Add2MyTemplateResultVO {
    /*添加过的*/
    private int addedFail;
    /*添加成功的*/
    private int addOK;

    public Add2MyTemplateResultVO(int addedFail, int addOK) {
        this.addedFail = addedFail;
        this.addOK = addOK;
    }

    public int getAddedFail() {
        return addedFail;
    }

    public void setAddedFail(int addedFail) {
        this.addedFail = addedFail;
    }

    public int getAddOK() {
        return addOK;
    }

    public void setAddOK(int addOK) {
        this.addOK = addOK;
    }
}
