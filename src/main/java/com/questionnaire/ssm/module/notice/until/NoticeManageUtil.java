package com.questionnaire.ssm.module.notice.until;

import com.questionnaire.ssm.module.notice.pojo.NoticeUnitVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 95884 on 2017/4/19.
 */
public class NoticeManageUtil {
    /**
     * 将选项条目转换为选项条目字符串
     *
     * @param unitVOList VO视图的发布对象单位
     * @return
     * @throws Exception
     */
    protected static String toNoticeUnitString(List<NoticeUnitVO> unitVOList) throws Exception {
        int unitSize = unitVOList.size();
        StringBuilder unitStrBuilder = new StringBuilder();
        for (int unitOrder = 0; unitOrder < unitSize; unitOrder++) {
            unitStrBuilder.append(unitVOList.get(unitOrder).getUnit());
            if (unitSize != unitOrder + 1) {
                unitStrBuilder.append("||");
            }
        }
        return unitStrBuilder.toString();
    }
    /**
     * 将数据库中单位字符串切割成正常的单位
     *
     * @param unitString
     * @return
     * @throws Exception
     */
    public static String toNoticeUnitItem(String unitString) {
        String[] units = null;
        units = unitString.split("\\|\\|");
        if (units == null) {
            return null;
        }
        String noticeUnitText=null;
        for(int order=0;order<units.length;order++){
            noticeUnitText=noticeUnitText+units[order]+" ";
        }
//        for(int order=0;order<units.length;order++){
//            noticeUnitVO=new NoticeUnitVO();
//            noticeUnitVO.setUnitOrder(order);
//            noticeUnitVO.setUnit(units[order]);
//            noticeUnitVOList.add(order,noticeUnitVO);
//        }
        return noticeUnitText;
    }

}
