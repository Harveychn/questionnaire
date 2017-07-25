package com.questionnaire.ssm.module.researchManage.mapper;

import com.questionnaire.ssm.module.generated.pojo.AnswerDetail;
import com.questionnaire.ssm.module.generated.pojo.AnswerPaper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/5/4.
 * Description: 调查结果处理mapper
 */
public interface ResearchResultMapper {

    /**
     * 批量插入答卷数据
     *
     * @param answerPaperList
     * @return
     * @throws Exception
     */
    int insert2AnswerPaperBatch(List<AnswerPaper> answerPaperList) throws Exception;
    /**
     * 批量插入答卷详细信息
     *
     * @param answerDetailList
     * @return
     * @throws Exception
     */
    int insert2AnswerDetailBatch(List<AnswerDetail> answerDetailList) throws Exception;
}
