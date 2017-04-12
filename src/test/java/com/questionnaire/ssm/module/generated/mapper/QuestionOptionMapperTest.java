package com.questionnaire.ssm.module.generated.mapper;

import com.questionnaire.ssm.module.questionnaireManage.enums.QuestionTypeEnum;
import com.questionnaire.ssm.module.questionnaireManage.pojo.QuestionOptionVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 郑晓辉 on 2017/3/27.
 * Description:
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class QuestionOptionMapperTest {
    @Resource
    private QuestionOptionMapper questionOptionMapper;

    @Test
    public void selectByPrimaryKey() throws Exception {
        List<QuestionOptionVO> questionOptionVOList = new ArrayList<>();

        QuestionOptionVO questionOptionVO = null;
        String optionsString = questionOptionMapper.selectByPrimaryKey(12l).getOptionString();

        String questionTypeCode = "1";
        String[] options = null;
        if (questionTypeCode.equals(QuestionTypeEnum.SINGLE_CHOICE.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.MULTIPLE_CHOICE.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.SINGLE_LINE_BLANK.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.MULTI_LINE_BLANK.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.DROP_SELECTION.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getCode())) {
            options = optionsString.split("\\|\\|");
        }

        assertNotEquals(options, null);

//        for (int order = 0; order < options.length; order++) {
//            questionOptionVO = new QuestionOptionVO();
//            questionOptionVO.setOptionOrder(order);
//            questionOptionVO.setOption(options[order]);
//            questionOptionVOList.add(order, questionOptionVO);
//        }

        for (QuestionOptionVO q : questionOptionVOList) {
            System.out.println(q.getOption());
            System.out.println(q.getOptionOrder());
            System.out.println("++++++++++++++++++++++++");
        }

    }

}