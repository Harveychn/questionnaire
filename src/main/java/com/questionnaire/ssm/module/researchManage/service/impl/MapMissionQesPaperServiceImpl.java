package com.questionnaire.ssm.module.researchManage.service.impl;

import com.questionnaire.ssm.module.generated.mapper.MappingMissionQuestionnaireMapper;
import com.questionnaire.ssm.module.generated.mapper.QuestionnaireMapper;
import com.questionnaire.ssm.module.generated.pojo.MappingMissionQuestionnaire;
import com.questionnaire.ssm.module.generated.pojo.MappingMissionQuestionnaireExample;
import com.questionnaire.ssm.module.generated.pojo.MissionWithBLOBs;
import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.researchManage.pojo.ResearchListVO;
import com.questionnaire.ssm.module.researchManage.pojo.ResearchQesPaperVO;
import com.questionnaire.ssm.module.researchManage.service.MapMissionQesPaperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/24.
 * Description:
 */
@Service
public class MapMissionQesPaperServiceImpl implements MapMissionQesPaperService {

    /**
     * 根据任务id获取问卷信息
     *
     * @param missionId 调查任务ID
     * @return
     * @throws Exception
     */
    @Override
    public List<ResearchQesPaperVO> listMissionQesPaperInfo(Long missionId) throws Exception {
        //构造查询条件
        MappingMissionQuestionnaireExample example = new MappingMissionQuestionnaireExample();
        example.createCriteria().andMissionIdEqualTo(missionId);
        //查询当前任务的map全部问卷id
        List<MappingMissionQuestionnaire> mappingMissionQuestionnaireList = null;
        try {
            mappingMissionQuestionnaireList = mappingMissionQuestionnaireMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_SELECT_FAIL, DBTableEnum.MAPPING_MISSION_QUESTIONNAIRE.getTableName());
        }
        List<ResearchQesPaperVO> researchQesPaperVOList = new ArrayList<>();
        ResearchQesPaperVO researchQesPaperVO = null;
        Questionnaire questionnaire = null;
        for (MappingMissionQuestionnaire map : mappingMissionQuestionnaireList) {
            researchQesPaperVO = new ResearchQesPaperVO();
            //设置当期那问卷最少提交量
            researchQesPaperVO.setMinSubmit(map.getMinSubmitCount());
            researchQesPaperVO.setQuestionnaireId(map.getQuestionnaireId());
            //查询问卷信息
            questionnaire = questionnaireMapper.selectByPrimaryKey(map.getQuestionnaireId());
            //问卷标题
            if (questionnaire.getQuestionnaireTitle() != null && !questionnaire.getQuestionnaireTitle().trim().equals("")) {
                researchQesPaperVO.setQuestionnaireTitle(questionnaire.getQuestionnaireTitle());
            }
            //问卷副标题
            if (questionnaire.getQuestionnaireSubtitle() != null && !questionnaire.getQuestionnaireSubtitle().trim().equals("")) {
                researchQesPaperVO.setQuestionnaireSubtitle(questionnaire.getQuestionnaireSubtitle());
            }
            //问卷描述
            if (questionnaire.getQuestionnaireDescription() != null && !questionnaire.getQuestionnaireDescription().trim().equals("")) {
                researchQesPaperVO.setQuestionnaireDescription(questionnaire.getQuestionnaireDescription());
            }
            researchQesPaperVOList.add(researchQesPaperVO);
        }
        return researchQesPaperVOList;
    }

    private QuestionnaireMapper questionnaireMapper;
    private MappingMissionQuestionnaireMapper mappingMissionQuestionnaireMapper;
    private final static Logger logger = LoggerFactory.getLogger(MapMissionQesPaperServiceImpl.class);

    @Autowired
    public MapMissionQesPaperServiceImpl(MappingMissionQuestionnaireMapper mappingMissionQuestionnaireMapper,
                                         QuestionnaireMapper questionnaireMapper) {
        this.mappingMissionQuestionnaireMapper = mappingMissionQuestionnaireMapper;
        this.questionnaireMapper = questionnaireMapper;
    }
}
