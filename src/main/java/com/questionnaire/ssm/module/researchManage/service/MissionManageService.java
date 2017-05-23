package com.questionnaire.ssm.module.researchManage.service;

import com.questionnaire.ssm.module.researchManage.pojo.ListMissionVO;

import java.util.List;

/**
 * Created by 95884 on 2017/5/23.
 */
public interface MissionManageService {
    List<ListMissionVO> listMission()throws Exception;
}
