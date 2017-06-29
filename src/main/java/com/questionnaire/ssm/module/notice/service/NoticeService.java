package com.questionnaire.ssm.module.notice.service;

import com.questionnaire.ssm.module.notice.pojo.CreateNoticeVO;
import com.questionnaire.ssm.module.notice.pojo.ListMyNoticeInfoVO;
import com.questionnaire.ssm.module.notice.pojo.NoticeForCurUserVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 95884 on 2017/4/1.
 */
public interface NoticeService {

    /**
     * 创建公告
     *
     * @param userTel
     * @param createNoticeVO
     * @throws Exception
     */
    void insertNotice(String userTel, CreateNoticeVO createNoticeVO) throws Exception;

    /**
     * 获取用户创建的公告信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    List<ListMyNoticeInfoVO> listNoticeByUserTel(String userTel) throws Exception;

    /**
     * 根据公告ID删除公告
     *
     * @param noticeId
     * @throws Exception
     */
    @Transactional
    void deleteNotice(Long noticeId) throws Exception;
}
