package com.questionnaire.ssm.module.notice.mapper;

import com.questionnaire.ssm.module.notice.pojo.NoticeForCurUserDTO;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/6/1.
 * Description: 对当前登录用户的公告信息
 */
public interface Notice2MeMapper {

    /**
     * 查询所有的已经发布的、提供给被发布人的公告信息
     *
     * @return
     * @throws Exception
     */
    List<NoticeForCurUserDTO> listAllNoticeForUserReleased() throws Exception;
}
