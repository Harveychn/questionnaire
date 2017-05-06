package com.questionnaire.ssm.module.notice.util;

import com.questionnaire.ssm.module.generated.pojo.NoticeWithBLOBs;
import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.notice.pojo.CreateNoticeVO;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/28.
 * Description: 提取VO数据到DO实体
 */
public class NoticeVODOUtil {
    /**
     * 获取创建公告视图中公告信息
     *
     * @param createNoticeVO
     * @return
     * @throws Exception
     */
    public static NoticeWithBLOBs toNoticeDO(CreateNoticeVO createNoticeVO) throws Exception {
        NoticeWithBLOBs noticeWithBLOBs = new NoticeWithBLOBs();
        //公告标题
        noticeWithBLOBs.setNoticeTitle(createNoticeVO.getNoticeTitle());
        //公告内容
        noticeWithBLOBs.setNoticeContext(createNoticeVO.getNoticeContent());
        //公告预计发布时间
        noticeWithBLOBs.setNoticeLaunchTime(createNoticeVO.getLaunchDate());

        List<Long> unitIdList = createNoticeVO.getUnitObjectIds();
        if (unitIdList.size() <= 0) {
            return noticeWithBLOBs;
        }
        StringBuilder unitIdString = new StringBuilder();
        for (int index = 0; index < unitIdList.size();
             index++) {
            unitIdString.append(unitIdList.get(index));
            if ((index + 1) < unitIdList.size()) {
                unitIdString.append(CONSTANT.getDivideSymbol());
            }
        }
        //公告单位id文本
        noticeWithBLOBs.setNoticeUnitText(unitIdString.toString());
        return noticeWithBLOBs;
    }
}
