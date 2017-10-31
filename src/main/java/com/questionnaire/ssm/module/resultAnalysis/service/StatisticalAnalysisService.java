package com.questionnaire.ssm.module.resultAnalysis.service;

import com.questionnaire.ssm.module.resultAnalysis.pojo.StatisticalAnalysisResultVO;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/5/30.
 * Description:
 */
public interface StatisticalAnalysisService {
    List<StatisticalAnalysisResultVO> getQesPaperAnalyzeResult(String missionId, String missionPaperId) throws Exception;
}
