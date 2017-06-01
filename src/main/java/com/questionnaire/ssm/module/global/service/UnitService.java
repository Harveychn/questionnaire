package com.questionnaire.ssm.module.global.service;

import com.questionnaire.ssm.module.generated.pojo.Unit;
import com.questionnaire.ssm.module.global.pojo.UnitInfoVO;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/27.
 * Description:
 */
public interface UnitService {
    /**
     * 通过单位ID获取单位信息
     *
     * @param unitId 单位id
     * @return
     * @throws Exception
     */
    Unit getUnitInfoByUnitId(Long unitId) throws Exception;


    List<UnitInfoVO> listUnitInfo() throws Exception;

    /**
     * 根据用户账户查询用户所在单位Id
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    Long getUnitIdByUserTel(String userTel) throws Exception;
}
