package com.questionnaire.ssm.module.researchManage.mapper;


import com.questionnaire.ssm.module.researchManage.pojo.MissionInfoVO;
import com.questionnaire.ssm.module.researchManage.pojo.MissionPaperDTO;
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

    List<ListMissionVO> listMission()throws Exception;

    /**
     * 查询可发布的问卷信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    List<QuestionnaireInfoVO> listReadyLaunchQesInfoByUserTel(@Param("userTel") String userTel) throws Exception;

    /**
     * 查询任务信息
     * 若userTel 为null 或者 ‘’ 则查询所有任务，
     * 否则查询传入userTel创建的任务信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    List<MissionInfoVO> listMissionInfo(@Param("userTel") String userTel) throws Exception;

    /**
     * 根据调查任务ID查询问卷信息
     *
     * @param missionId
     * @return
     * @throws Exception
     */
    List<MissionPaperDTO> listMissionPaperByMissionId(@Param("missionId") Long missionId) throws Exception;


    Long listCount(ListMissionVO listMissionVO)throws Exception;

    void changeFinalTime(Mission mission)throws Exception;

    Long findMission(ListMissionVO listMissionVO)throws Exception;
}
