package com.questionnaire.ssm.module.questionnaireManage.service;

import java.util.Date;

/**
 * Created by 郑晓辉 on 2017/3/30.
 * Description:
 */
public interface RecordActionService {

    void saveRecord(Date operateDate, Long questionnaireId, String actionCode) throws Exception;

    void saveMultiRecords(Date operateDate, Long[] questionnaireIds, String actionCode) throws Exception;
}
