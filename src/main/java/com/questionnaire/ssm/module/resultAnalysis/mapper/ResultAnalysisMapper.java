package com.questionnaire.ssm.module.resultAnalysis.mapper;

import com.questionnaire.ssm.module.resultAnalysis.pojo.ListAnswerPaperVO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.ListPrimaryDataInfoVO;

import java.util.List;

/**
 * Created by 95884 on 2017/5/5.
 * Description: 结果分析模块mapper
 * 查询问卷完成量原始数据
 */
public interface ResultAnalysisMapper {

    List<ListPrimaryDataInfoVO> listPrimaryDataInfo()throws Exception;
    Long listCount(ListPrimaryDataInfoVO listPrimaryDataInfoVO)throws Exception;
    List<ListAnswerPaperVO> listAnswerPaper(ListPrimaryDataInfoVO listPrimaryDataInfoVO)throws Exception;
}
