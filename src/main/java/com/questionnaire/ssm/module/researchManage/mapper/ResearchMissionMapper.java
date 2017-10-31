package com.questionnaire.ssm.module.researchManage.mapper;


import com.questionnaire.ssm.module.researchManage.pojo.MissionInfoVO;
import com.questionnaire.ssm.module.researchManage.pojo.MissionPaperDTO;
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
     * 查询进行中任务信息
     * 若userTel 为null 或者 ‘’ 则查询所有任务，
     * 否则查询userTel创建的任务信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    List<MissionInfoVO> listGoingMissionInfo(@Param("userTel") String userTel) throws Exception;

    /**
     * 查询已发布中的任务信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    List<MissionInfoVO> listReleasedMissions(@Param("userTel") String userTel) throws Exception;

    /**
     * 查询未发布的任务信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    List<MissionInfoVO> listUnreleasedMissions(@Param("userTel") String userTel) throws Exception;

    /**
     * 查询已经截止的任务信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    List<MissionInfoVO> listFinishMissions(@Param("userTel") String userTel) throws Exception;

    /**
     * 查询未截止的任务信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    List<MissionInfoVO> listUnfinishMissions(@Param("userTel") String userTel) throws Exception;

    /**
     * 根据调查任务ID查询问卷信息
     *
     * @param missionId
     * @return
     * @throws Exception
     */
    List<MissionPaperDTO> listMissionPaperByMissionId(@Param("missionId") Long missionId) throws Exception;

    Long listCount(ListMissionVO listMissionVO)throws Exception;

    Long findMission(ListMissionVO listMissionVO)throws Exception;

    Long findMissionByMissionId(Long missionId)throws Exception;
}
