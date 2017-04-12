package com.questionnaire.ssm.module.questionnaireManage.service;

import java.util.Date;

/**
 * Created by 郑晓辉 on 2017/3/30.
 * Description:
 */
public interface RecordActionService {

    /**
     * 单条对被操作的问卷记录保存
     *
     * @param operateDate     操作时间
     * @param questionnaireId 被操作的问卷
     * @param actionCode      动作代码
     * @throws Exception
     */
    void saveRecord(Date operateDate, Long questionnaireId, String actionCode) throws Exception;

    /**
     * 批量保存对被操作问卷/模板的操作动作以及时间
     *
     * @param operateDate      操作时间
     * @param questionnaireIds 被操作的问卷
     * @param actionCode       动作代码
     * @throws Exception
     */
    void saveMultiRecords(Date operateDate, Long[] questionnaireIds, String actionCode) throws Exception;
}
