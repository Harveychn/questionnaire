package com.questionnaire.ssm.module.qesTemplateManage.mapper;

import com.questionnaire.ssm.module.qesTemplateManage.pojo.PrivateTemplateInfoVO;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.PublicTemplateInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by 郑晓辉 on 2017/4/2.
 * Description:
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext*.xml"})
public class QesTemplateManageMapperTest {

    @Resource
    private QesTemplateManageMapper qesTemplateManageMapper;

    @Test
    public void listPublicTemplateInfo() throws Exception {
        for (PublicTemplateInfoVO publicInfo : qesTemplateManageMapper.listPublicTemplateInfo()) {
            System.out.println(publicInfo.getShareDate());
            System.out.println(publicInfo.toString());
        }

    }

    @Test
    public void listPrivateTemplateInfo() throws Exception {
        String userTel = "17764591959";
        for (PrivateTemplateInfoVO privateTemplateInfoVO : qesTemplateManageMapper.listPrivateTemplateInfo(userTel)) {
            System.out.println(privateTemplateInfoVO.toString());
        }
    }

}