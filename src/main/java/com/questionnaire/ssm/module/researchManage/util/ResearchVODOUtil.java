package com.questionnaire.ssm.module.researchManage.util;

import com.questionnaire.ssm.module.generated.pojo.MappingMissionQuestionnaire;
import com.questionnaire.ssm.module.generated.pojo.MissionWithBLOBs;
import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.researchManage.pojo.CreateResearchMissionVO;
import com.questionnaire.ssm.module.researchManage.pojo.MissionQesPaperVO;
import com.questionnaire.ssm.module.researchManage.pojo.ResearchQesPaperVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/24.
 * Description: 调查管理相关VO数据装换为DO数据
 */
public class ResearchVODOUtil {

    /**
     * 提取mission表数据
     *
     * @param userTel                 mission创建人
     * @param createResearchMissionVO 创建任务视图数据
     * @return
     * @throws Exception
     */
    public static MissionWithBLOBs getMissionDO(String userTel, CreateResearchMissionVO createResearchMissionVO) throws Exception {
        MissionWithBLOBs mission = new MissionWithBLOBs();
        //任务创建人
        if (userTel != null && !userTel.trim().equals("")) {
            mission.setCreateUser(userTel);
        }
        //任务描述设置
        if (createResearchMissionVO.getMissionDescription() != null
                && !createResearchMissionVO.getMissionDescription().trim().equals("")) {
            mission.setMissionDescription(createResearchMissionVO.getMissionDescription());
        }
        //任务开始时间设置
        if (createResearchMissionVO.getMissionStartDate() != null) {
            mission.setMissionStartDate(createResearchMissionVO.getMissionStartDate());
        }
        //任务结束时间设置
        if (createResearchMissionVO.getMissionDeadLine() != null) {
            mission.setMissionFinalDate(createResearchMissionVO.getMissionDeadLine());
        }
        //任务对象单位设置
        List<Long> missionObjectUnitIds = createResearchMissionVO.getMissionObjectUnitId();
        if (missionObjectUnitIds.size() > 0) {
            StringBuilder unitIdString = new StringBuilder();
            for (int index = 0; index < missionObjectUnitIds.size(); index++) {
                unitIdString.append(missionObjectUnitIds.get(index));
                if ((index + 1) < missionObjectUnitIds.size()) {
                    unitIdString.append(CONSTANT.getDivideSymbol());
                }
            }
            mission.setMissionUnitText(unitIdString.toString());
        }
        return mission;
    }

    /**
     * 提取数据库MappingMissionQuestionnaire 数据
     *
     * @param missionId 任务id
     * @param qesId     发布的问卷ID
     * @param minSubmit 最小提交量
     * @return
     * @throws Exception
     */
    public static MappingMissionQuestionnaire getMappingMissionQuestionnaireDO(Long missionId, Long qesId, Long minSubmit) throws Exception {
        MappingMissionQuestionnaire map = new MappingMissionQuestionnaire();
        //设置任务ID
        map.setMissionId(missionId);
        //设置问卷ID
        map.setQuestionnaireId(qesId);
        //设置问卷最低完成量
        map.setMinSubmitCount(minSubmit);
        return map;
    }

    /**
     * 过滤任务对象单位属于当前用户的任务
     *
     * @param userUnitId     当前任务单位ID
     * @param originMissions 未过滤的任务信息
     * @return
     * @throws Exception
     */
    public static List<MissionWithBLOBs> missionAfterFilter(Long userUnitId, List<MissionWithBLOBs> originMissions) throws Exception {
        List<MissionWithBLOBs> filterMissions = new ArrayList<>();
        String[] unitIds = null;
        for (MissionWithBLOBs missionWithBLOBs : originMissions) {
            unitIds = missionWithBLOBs.getMissionUnitText().split("\\|\\|");
            for (String unitId : unitIds) {
                if (unitId.equals(String.valueOf(userUnitId).trim())) {
                    filterMissions.add(missionWithBLOBs);
                    break;
                }
            }
        }
        return filterMissions;
    }

    /**
     * 复制用户已经选择好的问卷信息到发布问卷
     *
     * @param forCopyQes 用户选择要发布的数据库中问卷信息数据
     * @return 除问卷ID以外复制完成的问卷信息
     * @throws Exception
     */
    public static Questionnaire copyQesForPublish(Questionnaire forCopyQes) throws Exception {
        Questionnaire readyLaunchQes = new Questionnaire();
        readyLaunchQes.setQuestionnaireTitle(forCopyQes.getQuestionnaireTitle());

        if (forCopyQes.getQuestionnaireSubtitle() != null) {
            readyLaunchQes.setQuestionnaireSubtitle(forCopyQes.getQuestionnaireSubtitle());
        }
        if (forCopyQes.getQuestionnaireDescription() != null) {
            readyLaunchQes.setQuestionnaireDescription(forCopyQes.getQuestionnaireDescription());
        }
        readyLaunchQes.setCreateUser(forCopyQes.getCreateUser());
        readyLaunchQes.setCreateTime(forCopyQes.getCreateTime());

        readyLaunchQes.setIsTemplate(forCopyQes.getIsTemplate());
        readyLaunchQes.setIsShare(forCopyQes.getIsShare());
        readyLaunchQes.setIsVisible(true);
        readyLaunchQes.setIsDone(true);
        //设置是否发布问卷为true
        readyLaunchQes.setIsRelease(true);

        return readyLaunchQes;
    }
}
