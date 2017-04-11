package com.questionnaire.ssm.module.qesTemplateManage.mapper;

import com.questionnaire.ssm.module.qesTemplateManage.pojo.ListPrivateTemplateInfoVO;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.ListPublicTemplateInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

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
        for (ListPublicTemplateInfoVO publicInfo : qesTemplateManageMapper.listPublicTemplateInfo()) {
            System.out.println(publicInfo.getShareDate());
            System.out.println(publicInfo.toString());
        }

    }

    @Test
    public void listPrivateTemplateInfo() throws Exception {
        String userTel = "17764591959";
        for (ListPrivateTemplateInfoVO privateTemplateInfoVO : qesTemplateManageMapper.listPrivateTemplateInfo(userTel)) {
            System.out.println(privateTemplateInfoVO.toString());
        }
    }

}