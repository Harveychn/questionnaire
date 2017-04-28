package com.questionnaire.ssm.module.notice.service;

import com.questionnaire.ssm.module.notice.pojo.CreateNoticeVO;
import com.questionnaire.ssm.module.notice.pojo.Notice;

import java.util.List;

/**
 * Created by 95884 on 2017/4/1.
 */
public interface NoticeService {
    void insertNotice(String userTel, CreateNoticeVO createNoticeVO) throws Exception;

    List<Notice> listNoticeByUserTel(String userTel) throws Exception;

    void deleteNotice(Long noticeId) throws Exception;
}
