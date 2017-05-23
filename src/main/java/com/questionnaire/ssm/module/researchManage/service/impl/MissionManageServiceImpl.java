package com.questionnaire.ssm.module.researchManage.service.impl;

import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.researchManage.mapper.ResearchMissionMapper;
import com.questionnaire.ssm.module.researchManage.pojo.ListMissionVO;
import com.questionnaire.ssm.module.researchManage.service.MissionManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<ListMissionVO> listMission()throws Exception{
        List<ListMissionVO> listMissionVOList=new ArrayList<>();
        try{
            listMissionVOList=researchMissionMapper.listMission();
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR,"获取任务失败");
        }
        return listMissionVOList;
    }

    private ResearchMissionMapper researchMissionMapper;
    private static final Logger logger = LoggerFactory.getLogger(MissionManageServiceImpl.class);
    @Autowired
    public MissionManageServiceImpl(ResearchMissionMapper researchMissionMapper){
        this.researchMissionMapper=researchMissionMapper;
    }
}
