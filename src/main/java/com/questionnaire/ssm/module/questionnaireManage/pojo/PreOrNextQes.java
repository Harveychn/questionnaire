package com.questionnaire.ssm.module.questionnaireManage.pojo;

/**
 * Created by 郑晓辉 on 2017/6/29.
 * Description:
 */
public class PreOrNextQes {

    //已经超出边界的情况
    public final static long OUT_OF_INDEX = 0L;

    //我的全部问卷id信息
    private Long[] ids;
    //当前查看问卷id
    private Long curQesId;
    //上一份问卷ID
    private Long prevId;
    //下一份问卷ID
    private Long nextId;

    public PreOrNextQes(Long[] ids) {
        this.ids = ids;
    }

    public synchronized long getNextQesPaperId() throws Exception {
        return nextId;
    }

    public synchronized long getPreviousQesPaperId() throws Exception {
        return prevId;
    }

    //设置当前问卷id的同时，设置上、下一份的问卷ID，如果已经在边界，则为 OUT_OF_INDEX
    public void setCurrentQesPaperId(Long aPaperId) throws Exception {
        this.curQesId = aPaperId;
        int len = ids.length;
        for (int i = 0; i < len; i++) {
            if (aPaperId.equals(ids[i])) {
                prevId = i - 1 >= 0 ? ids[i - 1] : OUT_OF_INDEX;
                nextId = i + 1 < len ? ids[i + 1] : OUT_OF_INDEX;
            }
        }
    }

    public synchronized long getCurrentQesPaperId() throws Exception {
        return this.curQesId;
    }

}
