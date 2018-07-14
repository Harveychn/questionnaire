package com.questionnaire.ssm.module.resultAnalysis.mapper;

import com.questionnaire.ssm.module.resultAnalysis.pojo.AnswerInfoDO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.QuestionAnalyzeDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/5/30.
 * Description:
 */
public interface StatisticalAnalysisMapper {

    /**
     * 通过调查问卷id查询调查问卷原始问题信息
     *
     * @param qesId              调查问卷id
     * @param singleChoiceDBCode 单选题数据库存储代码
     * @param multiChoiceDBCode  多选题数据库存储代码
     * @return
     * @throws Exception
     */
    List<QuestionAnalyzeDO> listQuestionAnalyzeDOByQesPaperId(@Param("qesId") String qesId,
                                                              @Param("singleChoiceDBCode") String singleChoiceDBCode,
                                                              @Param("multiChoiceDBCode") String multiChoiceDBCode,
                                                              @Param("singlePicChoiceDBCode") String singlePicChoiceDBCode,
                                                              @Param("multiPicChoiceDBCode") String multiPicChoiceDBCode) throws Exception;

    /**
     * 通过调查问卷id、调查任务查询答案信息
     *
     * @param qesId              调查问卷id
     * @param missionId          调查任务id
     * @param singleChoiceDBCode 单选题数据库存储代码
     * @param multiChoiceDBCode  多选题数据库存储代码
     * @return
     * @throws Exception
     */
    List<AnswerInfoDO> listAnswerInfoDOByQesPaperIdMissionId(@Param("qesId") String qesId,
                                                             @Param("missionId") String missionId,
                                                             @Param("singleChoiceDBCode") String singleChoiceDBCode,
                                                             @Param("multiChoiceDBCode") String multiChoiceDBCode,
                                                             @Param("singlePicChoiceDBCode") String singlePicChoiceDBCode,
                                                             @Param("multiPicChoiceDBCode") String multiPicChoiceDBCode) throws Exception;
}
