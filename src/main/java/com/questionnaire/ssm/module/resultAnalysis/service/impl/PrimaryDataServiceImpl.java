package com.questionnaire.ssm.module.resultAnalysis.service.impl;

import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.resultAnalysis.controller.ResultAnalysisController;
import com.questionnaire.ssm.module.resultAnalysis.mapper.ResultAnalysisMapper;
import com.questionnaire.ssm.module.resultAnalysis.pojo.ListPrimaryDataInfoVO;
import com.questionnaire.ssm.module.resultAnalysis.service.PrimaryDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 95884 on 2017/5/5.
 */
@Service
public class PrimaryDataServiceImpl implements PrimaryDataService {
   public List<ListPrimaryDataInfoVO> listPrimaryData()throws Exception{
        List<ListPrimaryDataInfoVO> listPrimaryDataInfoVOList=new ArrayList<>();
        List<ListPrimaryDataInfoVO> listPrimaryDataInfoVOS=new ArrayList<>();
        try{
            listPrimaryDataInfoVOList=resultAnalysisMapper.listPrimaryDataInfo();
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR,"获取原始数据失败");
        }
        //获取问卷完成数量
        Long count=null;
        for(ListPrimaryDataInfoVO listPrimaryDataInfoVO:listPrimaryDataInfoVOList){
            count=resultAnalysisMapper.listCount(listPrimaryDataInfoVO);
            listPrimaryDataInfoVO.setQuestionnaireCount(count);
            try{
                listPrimaryDataInfoVOS.add(listPrimaryDataInfoVO);
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
        return listPrimaryDataInfoVOS;
    }


    private ResultAnalysisMapper resultAnalysisMapper;
    private static final Logger logger= LoggerFactory.getLogger(PrimaryDataServiceImpl.class);
    @Autowired
    public PrimaryDataServiceImpl(ResultAnalysisMapper resultAnalysisMapper){
        this.resultAnalysisMapper=resultAnalysisMapper;
    }
}
