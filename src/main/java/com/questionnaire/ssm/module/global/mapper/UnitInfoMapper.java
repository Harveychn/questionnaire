package com.questionnaire.ssm.module.global.mapper;

import com.questionnaire.ssm.module.global.pojo.UnitInfoVO;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/27.
 * Description: 单位信息mapper
 */
public interface UnitInfoMapper {
    /**
     * 获取单位信息
     *
     * @return
     * @throws Exception
     */
    List<UnitInfoVO> listUnitInfo() throws Exception;

    /**
     * 根据String类型的单位id查询单位名
     * @param unitIds
     * @return
     * @throws Exception
     */
    List<String> listUnitNameByUnitIDs(List<String> unitIds)throws Exception;
}
