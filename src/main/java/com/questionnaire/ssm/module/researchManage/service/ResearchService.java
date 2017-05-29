package com.questionnaire.ssm.module.researchManage.service;

import com.questionnaire.ssm.module.researchManage.enums.MissionStatusEnum;
import com.questionnaire.ssm.module.researchManage.pojo.CreateResearchMissionVO;
import com.questionnaire.ssm.module.researchManage.pojo.MissionInfoVO;
import com.questionnaire.ssm.module.researchManage.pojo.QuestionnaireInfoVO;
import com.questionnaire.ssm.module.researchManage.pojo.ResearchListVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/24.
 * Description: 调查管理service
 */
public interface ResearchService {
    List<QuestionnaireInfoVO> listQesInfoByUserTel(String userTel) throws Exception;

    /**
     * 创建任务
     *
     * @param userTel                 创建人
     * @param createResearchMissionVO 创建视图数据
     * @throws Exception
     */
    @Transactional
    void createMissionByUserTel(String userTel, CreateResearchMissionVO createResearchMissionVO) throws Exception;

    /**
     * 查询用户可见的而且未到截止日期的调查任务
     *
     * @param userTel 用户账户
     * @return
     * @throws Exception
     */
    List<ResearchListVO> listResearchMission(String userTel) throws Exception;

    /**
     * 通过任务状态查询任务信息
     * userTel为null或者‘’则查询全部信息
     *
     * @param userTel
     * @param missionStatusEnum
     * @return
     * @throws Exception
     */
    List<MissionInfoVO> listMissionInfo(String userTel, MissionStatusEnum missionStatusEnum) throws Exception;

}
