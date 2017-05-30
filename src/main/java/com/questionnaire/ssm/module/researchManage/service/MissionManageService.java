package com.questionnaire.ssm.module.researchManage.service;

import com.questionnaire.ssm.module.generated.pojo.Mission;
import com.questionnaire.ssm.module.researchManage.pojo.ListMissionVO;
import com.questionnaire.ssm.module.researchManage.pojo.MissionVO;

import java.util.List;

/**
 * Created by 95884 on 2017/5/23.
 */
public interface MissionManageService {
    List<ListMissionVO> listMission()throws Exception;
    void changeFinalTime(Long missionId,MissionVO missionVO)throws Exception;
    void deleteMission(Long missionId,Long questionnaireId)throws Exception;
}
