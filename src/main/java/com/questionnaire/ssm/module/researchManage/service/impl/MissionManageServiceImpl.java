package com.questionnaire.ssm.module.researchManage.service.impl;

import com.questionnaire.ssm.module.generated.mapper.MappingMissionQuestionnaireMapper;
import com.questionnaire.ssm.module.generated.mapper.MissionMapper;
import com.questionnaire.ssm.module.generated.mapper.QuestionnaireMapper;
import com.questionnaire.ssm.module.generated.pojo.*;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.researchManage.mapper.ResearchMissionMapper;
import com.questionnaire.ssm.module.researchManage.pojo.ListMissionVO;
import com.questionnaire.ssm.module.researchManage.pojo.MissionVO;
import com.questionnaire.ssm.module.researchManage.service.MissionManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 95884 on 2017/5/23.
 */
@Service
public class MissionManageServiceImpl implements MissionManageService {
    /**
     * 获取任务
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<ListMissionVO> listMission() throws Exception {
        List<ListMissionVO> listMissionVOList = new ArrayList<>();
        List<ListMissionVO> listMissionVOS = new ArrayList<>();
        try {
            listMissionVOList = researchMissionMapper.listMission();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, "获取任务失败");
        }
        //获取任务完成量
        Long count = null;
        for (ListMissionVO listMissionVO : listMissionVOList) {
            count = researchMissionMapper.listCount(listMissionVO);
            listMissionVO.setQuestionnaireCount(count);

            Date date = new Date();
            //设置要获取到什么样的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取String类型的时间
            String createdate = sdf.format(date);
            //比较当前时间与结束时间
            int fi = createdate.compareTo(listMissionVO.getMissionFinalDate());
            //比较当前时间与开始时间
            int st = createdate.compareTo(listMissionVO.getMissionStartDate());

            if (count > listMissionVO.getMinSubmitCount()) {//当前提交量大于最低提交量
                listMissionVO.setMissionStatus("已完成");
            } else if (fi > 0) {//当前时间大于结束时间
                listMissionVO.setMissionStatus("已截止");
            } else if (count < listMissionVO.getMinSubmitCount() && count > 0) {//当前提交量大于0小于最低提交量
                listMissionVO.setMissionStatus("进行中");
            } else if (count == 0 || st < 0) {//当前提交量等于0或者当前时间小于开始时间
                listMissionVO.setMissionStatus("未开始");
            }

            try {
                listMissionVOS.add(listMissionVO);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return listMissionVOS;
    }

    /**
     * 修改任务截止时间
     *
     * @throws Exception
     */
    @Override
    @Transactional
    public void changeFinalTime(Long missionId, MissionVO missionVO) throws Exception {
        MissionWithBLOBs missionWithBLOBs = new MissionWithBLOBs();
        missionWithBLOBs = missionMapper.selectByPrimaryKey(missionId);

        //字符串日期类型转换为date类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
        String dstr = missionVO.getMissionFinalDate();
        Date date = sdf.parse(dstr);

        missionWithBLOBs.setMissionFinalDate(date);
        missionMapper.updateByPrimaryKeySelective(missionWithBLOBs);
    }

    /**
     *
     * 删除任务
     *
     * @throws Exception
     */
    @Override
    public void deleteMission(Long missionId, Long questionnaireId) throws Exception {
        Long checkMissionId = null;
        ListMissionVO listMissionVO = new ListMissionVO();
        listMissionVO.setMissionId(missionId);
        listMissionVO.setQuestionnaireId(questionnaireId);
        //删除关系
        Long id = researchMissionMapper.findMission(listMissionVO);
        mappingMissionQuestionnaireMapper.deleteByPrimaryKey(id);
        //删除问卷
        questionnaireMapper.deleteByPrimaryKey(questionnaireId);
        //在mapping_mission_questionnaire表中查找还有没有该任务id
        checkMissionId = researchMissionMapper.findMissionByMissionId(missionId);
        if (checkMissionId == null) {//如果找不到，删除任务
            missionMapper.deleteByPrimaryKey(missionId);
        }
    }

    private ResearchMissionMapper researchMissionMapper;
    private MissionMapper missionMapper;
    private MappingMissionQuestionnaireMapper mappingMissionQuestionnaireMapper;
    private QuestionnaireMapper questionnaireMapper;
    private static final Logger logger = LoggerFactory.getLogger(MissionManageServiceImpl.class);

    @Autowired
    public MissionManageServiceImpl(ResearchMissionMapper researchMissionMapper,
                                    MissionMapper missionMapper,
                                    MappingMissionQuestionnaireMapper mappingMissionQuestionnaireMapper,
                                    QuestionnaireMapper questionnaireMapper) {
        this.researchMissionMapper = researchMissionMapper;
        this.missionMapper = missionMapper;
        this.mappingMissionQuestionnaireMapper = mappingMissionQuestionnaireMapper;
        this.questionnaireMapper = questionnaireMapper;
    }
}
