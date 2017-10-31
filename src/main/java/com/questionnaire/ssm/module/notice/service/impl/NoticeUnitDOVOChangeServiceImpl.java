package com.questionnaire.ssm.module.notice.service.impl;

import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.notice.service.NoticeUnitDOVOChangeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/6/1.
 * Description: 视图数据中的单位信息与数据库中实际存储的单位信息转换
 */
@Service
public class NoticeUnitDOVOChangeServiceImpl implements NoticeUnitDOVOChangeService {

    /**
     * 将视图数据中的单位idList装换为数据库中单位ID字符串
     *
     * @param unitIdList
     * @return
     * @throws Exception
     */
    @Override
    public String toDOUnitString(List<Long> unitIdList) throws Exception {
        if (unitIdList.size() <= 0) {
            return null;
        }
        StringBuilder unitIdString = new StringBuilder();
        for (int index = 0; index < unitIdList.size();
             index++) {
            unitIdString.append(unitIdList.get(index));
            if ((index + 1) < unitIdList.size()) {
                unitIdString.append(CONSTANT.getDivideSymbol());
            }
        }
        return unitIdString.toString();
    }


//    /**
//     * 将数据库中存储的单位id字符串装换为视图数据中可见的单位信息
//     *
//     * @param unitIdString
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public List<String> toVOUnitList(String unitIdString) throws Exception {
//
//    }

}
