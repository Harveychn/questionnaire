package com.questionnaire.ssm.module.notice.mapper;


import com.questionnaire.ssm.module.notice.pojo.ListMyNoticeDTO;
import com.questionnaire.ssm.module.notice.pojo.NoticeForSurveyorDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeManageMapper {

    List<ListMyNoticeDTO> listNoticeByUserTel(@Param("userTel") String userTel) throws Exception;

    List<NoticeForSurveyorDTO> listNoticeInfoForSurveyor()throws Exception;
}