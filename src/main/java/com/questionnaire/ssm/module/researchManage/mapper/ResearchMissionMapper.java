package com.questionnaire.ssm.module.researchManage.mapper;

import com.questionnaire.ssm.module.generated.pojo.MappingMissionQuestionnaire;
import com.questionnaire.ssm.module.generated.pojo.Mission;
import com.questionnaire.ssm.module.researchManage.pojo.ListMissionVO;
import com.questionnaire.ssm.module.researchManage.pojo.QuestionnaireInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/27.
 * Description: 调查任务管理mapper
 */
public interface ResearchMissionMapper {

    List<QuestionnaireInfoVO> listReadyLaunchQesInfoByUserTel(@Param("userTel") String userTel)throws Exception;

    List<ListMissionVO> listMission()throws Exception;

    Long listCount(ListMissionVO listMissionVO)throws Exception;

    void changeFinalTime(Mission mission)throws Exception;

    Long findMission(ListMissionVO listMissionVO)throws Exception;
}
