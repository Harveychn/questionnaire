package com.questionnaire.ssm.module.generated.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by 郑晓辉 on 2017/4/7.
 * Description:
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class RecordOperateQuestionnaireMapperTest {
    @Resource
    private RecordOperateQuestionnaireMapper recordOperateQuestionnaireMapper;

    @Test
    public void selectByExample() throws Exception {
        Long questionnaireId = 24L;
        RecordOperateQuestionnaireExample example = new RecordOperateQuestionnaireExample();
        example.createCriteria().andQuestionnaireIdEqualTo(questionnaireId);
        example.setOrderByClause("operate_date DESC LIMIT 1");

        assertEquals(1, recordOperateQuestionnaireMapper.selectByExample(example).size());
        for (RecordOperateQuestionnaire record : recordOperateQuestionnaireMapper.selectByExample(example)) {
            System.out.println(record.getAction() + "||" + record.getOperateDate());
        }
    }

}