package com.questionnaire.ssm.module.qesTemplateManage.mapper;

import com.questionnaire.ssm.module.qesTemplateManage.pojo.PrivateTemplateInfoVO;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.PublicTemplateInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/2.
 * Description:
 */
public interface QesTemplateManageMapper {

    /**
     * 查询个人模板信息
     *
     * @param userTel 要查询的用户账户
     * @return
     * @throws Exception
     */
    List<PrivateTemplateInfoVO> listPrivateTemplateInfo(@Param("userTel") String userTel) throws Exception;

    /**
     * 获取公共模板信息
     *
     * @return
     * @throws Exception
     */
    List<PublicTemplateInfoVO> listPublicTemplateInfo() throws Exception;
}
