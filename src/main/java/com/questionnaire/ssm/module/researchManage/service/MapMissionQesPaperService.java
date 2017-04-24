package com.questionnaire.ssm.module.researchManage.service;

import com.questionnaire.ssm.module.researchManage.pojo.ResearchQesPaperVO;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/24.
 * Description:
 */
public interface MapMissionQesPaperService {
    List<ResearchQesPaperVO> listMissionQesPaperInfo(Long missionId) throws Exception;
}
