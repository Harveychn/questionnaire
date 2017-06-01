package com.questionnaire.ssm.module.notice.service;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/6/1.
 * Description:视图数据中的单位信息与数据库中实际存储的单位信息转换
 */
public interface NoticeUnitDOVOChangeService {

    /**
     * 将视图数据中的单位idList装换为数据库中单位ID字符串
     *
     * @param unitIdList
     * @return
     * @throws Exception
     */
    String toDOUnitString(List<Long> unitIdList) throws Exception;

//    /**
//     * 将数据库中存储的单位id字符串装换为视图数据中可见的单位信息
//     *
//     * @param unitIdString
//     * @return
//     * @throws Exception
//     */
//    List<String> toVOUnitList(String unitIdString) throws Exception;
}
