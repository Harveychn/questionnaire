package com.questionnaire.ssm.module.notice.service;

import com.questionnaire.ssm.module.notice.enums.NoticeForCurUserActionEnum;
import com.questionnaire.ssm.module.notice.pojo.NoticeForCurUserVO;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/6/1.
 * Description: 对当前在线用户的已经发布的公告信息
 */
public interface Notice2MeService {
    /**
     * 获取对当前用户的、已经发布的功能信息
     *
     * @param curUserAccount 当前用户账户
     * @param actionEnum     获取公告的动作枚举
     * @return
     * @throws Exception
     */
    List<NoticeForCurUserVO> getReleasedNoticeForCurrentUser(String curUserAccount, NoticeForCurUserActionEnum actionEnum) throws Exception;
}
