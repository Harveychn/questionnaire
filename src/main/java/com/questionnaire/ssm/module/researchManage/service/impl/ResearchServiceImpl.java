package com.questionnaire.ssm.module.researchManage.service.impl;

import com.questionnaire.ssm.module.generated.mapper.*;
import com.questionnaire.ssm.module.generated.pojo.*;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.global.mapper.UnitInfoMapper;
import com.questionnaire.ssm.module.global.service.Add2LibraryService;
import com.questionnaire.ssm.module.researchManage.enums.MissionStatusEnum;
import com.questionnaire.ssm.module.researchManage.mapper.ResearchMissionMapper;
import com.questionnaire.ssm.module.researchManage.pojo.*;
import com.questionnaire.ssm.module.researchManage.service.MapMissionQesPaperService;
import com.questionnaire.ssm.module.researchManage.service.ResearchService;
import com.questionnaire.ssm.module.researchManage.util.ResearchVODOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/24.
 * Description: 任务调查管理
 */
@Service
public class ResearchServiceImpl implements ResearchService {

    /**
     * 通过用户查询可发布问卷信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    @Override
    public List<QuestionnaireInfoVO> listQesInfoByUserTel(String userTel) throws Exception {
        return researchMissionMapper.listReadyLaunchQesInfoByUserTel(userTel);
    }

    /**
     * 创建问卷
     *
     * @param userTel                 创建人
     * @param createResearchMissionVO 创建视图数据
     * @throws Exception
     */
    @Override
    @Transactional
    public void createMissionByUserTel(String userTel, CreateResearchMissionVO createResearchMissionVO) throws Exception {
        //获取视图数据中属于mission数据库表数据，missionID未赋值
        MissionWithBLOBs missionWithBLOBs = ResearchVODOUtil.getMissionDO(userTel, createResearchMissionVO);
        //保存调查任务信息
        try {
            missionMapper.insertSelective(missionWithBLOBs);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL, DBTableEnum.MISSION.getTableName());
        }
        Long missionId = missionWithBLOBs.getMissionId();
        //获取视图数据中属于 mission_questionnaire的数据
        List<MissionQesPaperVO> missionQesPaperVOList = createResearchMissionVO.getMissionQuestionnaireInfo();
        //保存map关系数据
        MappingMissionQuestionnaire mappingMissionQuestionnaire = null;
        Questionnaire copiedQes = null;
        for (MissionQesPaperVO missionQesPaperVO : missionQesPaperVOList) {
            //根据用户选择的问卷信息、复制一份发布问卷，将新的发布问卷信息保存下来
            Questionnaire qesForCopyDO = questionnaireMapper.selectByPrimaryKey(missionQesPaperVO.getQuestionnaireId());
            if (qesForCopyDO == null) {
                continue;
            }
            copiedQes = ResearchVODOUtil.copyQesForPublish(qesForCopyDO);
//            2017-7-10 修复空问卷信息bug
//            //保存发布的问卷信息
//            try {
//                questionnaireMapper.insertSelective(copiedQes);
//            } catch (Exception e) {
//                logger.error(e.getMessage());
//                throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL,
//                        DBTableEnum.QUESTIONNAIRE.getTableName());
//            }
//
//            //组织映射关系
//            mappingMissionQuestionnaire = ResearchVODOUtil.getMappingMissionQuestionnaireDO(missionId,
//                    copiedQes.getQuestionnaireId(), missionQesPaperVO.getMinSubmit());
//            组织映射关系
            mappingMissionQuestionnaire = ResearchVODOUtil.getMappingMissionQuestionnaireDO(missionId,
                    add2LibraryService.copiedQesPaperId(qesForCopyDO.getQuestionnaireId(), copiedQes), missionQesPaperVO.getMinSubmit());

            try {
                mappingMissionQuestionnaireMapper.insertSelective(mappingMissionQuestionnaire);
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL, DBTableEnum.MAPPING_MISSION_QUESTIONNAIRE.getTableName());
            }
        }
    }

    /**
     * 获取用户可见且未到截止日期的调查任务
     *
     * @param userTel 在线用户名
     * @return
     * @throws Exception
     */
    @Override
    public List<ResearchListVO> listResearchMission(String userTel) throws Exception {
        //当前用户所属单位id
        Long userUnitId = 0L;
        try {
            userUnitId = userMapper.selectByPrimaryKey(userTel).getUnitId();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_SELECT_FAIL, DBTableEnum.USER.getTableName());
        }

        //根据用户所在单位id查询、以及当前日期查询调查任务信息
        MissionExample missionExample = new MissionExample();
        missionExample.createCriteria()
                .andMissionFinalDateGreaterThan(new Date());
        missionExample.setOrderByClause("mission_start_date DESC");
        List<MissionWithBLOBs> allMissionList = null;
        try {
            allMissionList = missionMapper.selectByExampleWithBLOBs(missionExample);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_SELECT_FAIL, DBTableEnum.MISSION.getTableName());
        }
        //移除任务单位不是当前用户所在单位ID
        List<MissionWithBLOBs> filterMissionList = ResearchVODOUtil.missionAfterFilter(userUnitId, allMissionList);
        //获取每一个任务的问卷信息，组织成vo数据
        List<ResearchListVO> researchListVOList = new ArrayList<>();
        List<ResearchQesPaperVO> researchQesPaperVOList = null;
        ResearchListVO researchListVO = null;
        for (MissionWithBLOBs currentMission : filterMissionList) {
            researchListVO = new ResearchListVO();
            researchListVO.setResearchId(currentMission.getMissionId());
            researchListVO.setResearchLaunchDate(currentMission.getMissionStartDate());
            researchListVO.setResearchDeadline(currentMission.getMissionFinalDate());
            researchQesPaperVOList = mapMissionQesPaperService.listMissionQesPaperInfo(currentMission.getMissionId());
            if (researchQesPaperVOList.size() > 0) {
                researchListVO.setResearchQesPaperVOList(researchQesPaperVOList);
            }
            //添加当前处理调查任务到调查任务List
            researchListVOList.add(researchListVO);
        }

        return researchListVOList;
    }

    /**
     * * 查询任务信息
     * userTel为null或者‘’则查询全部信息
     *
     * @param userTel
     * @param missionStatusEnum 要查询任务的状态
     * @return
     * @throws Exception
     */
    @Override
    public List<MissionInfoVO> listMissionInfo(String userTel, MissionStatusEnum missionStatusEnum) throws Exception {
        List<MissionInfoVO> missionInfoVOList = null;

        switch (missionStatusEnum) {
            case RELEASED_STATUS://发布中任务信息
                missionInfoVOList = researchMissionMapper.listReleasedMissions(userTel);
                break;
            case UNRELEASED_STATUS://未发布的任务信息
                missionInfoVOList = researchMissionMapper.listUnreleasedMissions(userTel);
                break;
            case FINISH_STATUS://已截止的任务信息
                missionInfoVOList = researchMissionMapper.listFinishMissions(userTel);
                break;
            case UNFINISHED_STATUS://未截止的任务信息
                missionInfoVOList = researchMissionMapper.listUnfinishMissions(userTel);
                break;
            case GOING_STATUS://进行中的任务信息
                missionInfoVOList = researchMissionMapper.listGoingMissionInfo(userTel);
                break;
            default:
                break;
        }
        if (missionInfoVOList == null || missionInfoVOList.size() <= 0) {
            return null;
        }

        List<String> unitNameList = null;
        List<MissionPaperDTO> missionPaperDTOList = null;
        for (MissionInfoVO missionInfoVO : missionInfoVOList) {
            String executeUnitIdStr = missionInfoVO.getUnitIdStr();
            String[] executeUnitIds = executeUnitIdStr.split("\\|\\|");
            unitNameList = unitInfoMapper.listUnitNameByUnitIDs(Arrays.asList(executeUnitIds));
            //设置执行单位
            if (unitNameList.size() > 0) {
                missionInfoVO.setExecuteUnitList(unitNameList);
            }
            unitNameList = null;

            //设置任务相关问卷信息
            missionPaperDTOList = researchMissionMapper.listMissionPaperByMissionId(missionInfoVO.getMissionId());
            if (missionPaperDTOList.size() > 0) {
                missionInfoVO.setMissionPaperDTOList(missionPaperDTOList);
            }
            missionPaperDTOList = null;
        }

        return missionInfoVOList;
    }


    private UnitInfoMapper unitInfoMapper;
    private UserMapper userMapper;
    private MissionMapper missionMapper;
    private MappingMissionQuestionnaireMapper mappingMissionQuestionnaireMapper;
    private MapMissionQesPaperService mapMissionQesPaperService;
    private ResearchMissionMapper researchMissionMapper;
    private QuestionnaireMapper questionnaireMapper;
    private Add2LibraryService add2LibraryService;

    @Autowired
    public ResearchServiceImpl(UnitInfoMapper unitInfoMapper,
                               MissionMapper missionMapper,
                               MappingMissionQuestionnaireMapper mappingMissionQuestionnaireMapper,
                               UserMapper userMapper,
                               MapMissionQesPaperService mapMissionQesPaperService,
                               ResearchMissionMapper researchMissionMapper,
                               QuestionnaireMapper questionnaireMapper,
                               Add2LibraryService add2LibraryService) {
        this.unitInfoMapper = unitInfoMapper;
        this.missionMapper = missionMapper;
        this.mappingMissionQuestionnaireMapper = mappingMissionQuestionnaireMapper;
        this.userMapper = userMapper;
        this.mapMissionQesPaperService = mapMissionQesPaperService;
        this.researchMissionMapper = researchMissionMapper;
        this.questionnaireMapper = questionnaireMapper;
        this.add2LibraryService = add2LibraryService;
    }

    private final static Logger logger = LoggerFactory.getLogger(ResearchServiceImpl.class);
}
