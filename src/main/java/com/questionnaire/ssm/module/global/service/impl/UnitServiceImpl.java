package com.questionnaire.ssm.module.global.service.impl;

import com.questionnaire.ssm.module.generated.mapper.UnitMapper;
import com.questionnaire.ssm.module.generated.pojo.Unit;
import com.questionnaire.ssm.module.global.mapper.UnitInfoMapper;
import com.questionnaire.ssm.module.global.pojo.UnitInfoVO;
import com.questionnaire.ssm.module.global.service.UnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/27.
 * Description: 单位操作相关服务
 */
@Service
public class UnitServiceImpl implements UnitService {

    /**
     * 通过单位id获取单位信息
     *
     * @param unitId
     * @return
     * @throws Exception
     */
    @Override
    public Unit getUnitInfoByUnitId(Long unitId) throws Exception {
        return unitMapper.selectByPrimaryKey(unitId);
    }

    /**
     * 获取unitInfo List
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<UnitInfoVO> listUnitInfo() throws Exception {
        return unitInfoMapper.listUnitInfo();
    }

    private final static Logger logger = LoggerFactory.getLogger(UnitServiceImpl.class);
    private UnitMapper unitMapper;
    private UnitInfoMapper unitInfoMapper;

    @Autowired
    public UnitServiceImpl(UnitMapper unitMapper, UnitInfoMapper unitInfoMapper) {
        this.unitMapper = unitMapper;
        this.unitInfoMapper = unitInfoMapper;
    }
}
