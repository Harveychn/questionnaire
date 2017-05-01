package com.questionnaire.ssm.module.notice.mapper;


import com.questionnaire.ssm.module.notice.pojo.ListNoticeDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeManageMapper {

    List<ListNoticeDTO> listNoticeByUserTel(@Param("userTel") String userTel) throws Exception;
}