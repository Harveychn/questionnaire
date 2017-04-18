package com.questionnaire.ssm.module.generated.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by 郑晓辉 on 2017/4/17.
 * Description:
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class UnitMapperTest {

    @Resource
    private UnitMapper unitMapper;

    @Test
    public void insertSelective() throws Exception {
        Unit unit = new Unit();
        unit.setUnitCode("01");
        unit.setUnitName("杭州电子科技大学");
        assertEquals(unitMapper.insertSelective(unit), 1);
    }

}