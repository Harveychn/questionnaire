package com.questionnaire.ssm.module.qesTemplateManage.mapper;

import com.questionnaire.ssm.module.qesTemplateManage.pojo.ListPrivateTemplateInfoVO;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.ListPublicTemplateInfoVO;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 查询个人模板信息
     *
     * @param userTel 要查询的用户账户
     * @return
     * @throws Exception
     */
    List<ListPrivateTemplateInfoVO> listPrivateTemplateInfo(@Param("userTel") String userTel) throws Exception;
}
