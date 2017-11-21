package com.questionnaire.ssm.module.resultAnalysis.service;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * Created by 郑晓辉 on 2017/7/22.
 * Description: 答案结果文本数据导出
 */
public interface AnswerTxtExportService {

    /**
     * 获取工作蒲对象，其中数据已经组织完成
     *
     * @param missionID
     * @param qesID
     * @return
     * @throws Exception
     */
    Workbook getExcelFile(Long missionID, Long qesID) throws Exception;
}
