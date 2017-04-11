package com.questionnaire.ssm.module.questionnaireManage.mapper;

import com.questionnaire.ssm.module.questionnaireManage.pojo.ListQuestionnaireVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/27.
 * Description:
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class QesManageMapperTest {

    @Resource
    private QesManageMapper qesManageMapper;

    @Test
    public void selectQuestionnaireInfoByUserTel() throws Exception {
        List<ListQuestionnaireVO> listQuestionnaireVOList = qesManageMapper.listQuestionnaireInfoByUserTel(null);
        for (ListQuestionnaireVO listQuestionnaireVO : listQuestionnaireVOList) {
            System.out.println(listQuestionnaireVO.getQuestionnaireId());
        }

    }

}