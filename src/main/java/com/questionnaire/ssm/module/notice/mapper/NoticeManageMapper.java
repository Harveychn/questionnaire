package com.questionnaire.ssm.module.notice.mapper;


import com.questionnaire.ssm.module.notice.pojo.ListMyNoticeDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeManageMapper {

    /**
     * 查询用户创建的已发布的公告信息
     *
     * @param userTel 用户名
     * @return
     * @throws Exception
     */
    List<ListMyNoticeDTO> listNoticeByUserTel(@Param("userTel") String userTel) throws Exception;

//    List<NoticeForCurUserDTO> listNoticeInfoForSurveyor() throws Exception;
}