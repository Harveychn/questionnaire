package com.questionnaire.ssm.module.resultAnalysis.service;

import com.questionnaire.ssm.module.resultAnalysis.pojo.ListPrimaryDataInfoVO;

import java.util.List;

/**
 * Created by 95884 on 2017/5/7.
 */
public interface PrimaryDataService {
    List<ListPrimaryDataInfoVO> listPrimaryData()throws Exception;
}
