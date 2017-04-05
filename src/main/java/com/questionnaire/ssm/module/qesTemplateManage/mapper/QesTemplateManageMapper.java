package com.questionnaire.ssm.module.qesTemplateManage.mapper;

import com.questionnaire.ssm.module.qesTemplateManage.pojo.ListPublicTemplateInfoVO;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/2.
 * Description:
 */
public interface QesTemplateManageMapper {

    /**
     * 获取公共模板信息
     *
     * @return
     * @throws Exception
     */
    List<ListPublicTemplateInfoVO> listPublicTemplateInfo() throws Exception;
}
