package com.questionnaire.ssm.module.resultAnalysis.mapper;

import com.questionnaire.ssm.module.resultAnalysis.pojo.AnswerTxtExportDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/7/22.
 * Description: 答卷结果数据导出
 */
public interface ResultExportMapper {

    /**
     * 查询编号为 missionID 的调查，且编号为 qesID 的问卷的所有答卷中除了 exceptTypeList 中的数据库问题代码外的问题信息
     *
     * @param missionID      调查编号
     * @param qesID          调查问卷编号
     * @param exceptTypeList 排除的问题信息
     * @return
     * @throws Exception
     */
    List<AnswerTxtExportDO> listResultTxtByIdsAndTypes(@Param("missionID") Long missionID,
                                                       @Param("qesID") Long qesID,
                                                       @Param("exceptTypeList") List<String> exceptTypeList) throws Exception;
}
