package com.questionnaire.ssm.module.questionnaireManager.util;


import com.questionnaire.ssm.module.generated.pojo.Question;
import com.questionnaire.ssm.module.generated.pojo.QuestionOption;
import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import com.questionnaire.ssm.module.questionnaireManager.enums.OptionStrDivideEnum;
import com.questionnaire.ssm.module.questionnaireManager.pojo.CreateQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManager.pojo.QuestionDO;
import com.questionnaire.ssm.module.questionnaireManager.pojo.QuestionDTO;
import com.questionnaire.ssm.module.questionnaireManager.pojo.QuestionOptionDO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:用于前台VO实体转换为后台数据库实体类
 */
public class QuestionnaireObjUtil {

    /**
     * 提取视图中问卷数据到数据库 questionnaire 表,其中 questionnaire_id 由数据库自动生成
     *
     * @param questionnaireVO 问卷信息
     * @return
     * @throws Exception
     */
    public static Questionnaire toQuestionnaireDO(CreateQuestionnaireVO questionnaireVO) throws Exception {
        Questionnaire questionnaire = new Questionnaire();

        questionnaire.setQuestionnaireTitle(questionnaireVO.getQuestionnaireTitle());
        questionnaire.setQuestionnaireSubtitle(questionnaireVO.getQuestionnaireSubtitle());
        questionnaire.setQuestionnaireDescription(questionnaireVO.getQuestionnaireDescription());
        questionnaire.setIsDone(questionnaireVO.getDone());
        questionnaire.setIsTemplate(questionnaireVO.getTemplate());

        questionnaire.setIsDelete(false);
        questionnaire.setIsShare(false);
        questionnaire.setIsVisiable(true);

        return questionnaire;
    }

    /**
     * 提取问卷中题目的题目信息以及题目的选项数据信息，question_id 以及 option_id 均为赋值
     * 需要在将数据插入的数据库是赋值
     *
     * @param questions 问卷题目信息
     * @return
     * @throws Exception
     */
    public static QuestionDTO toQuestionMultiDO(List<QuestionDO> questions) throws Exception {
        List<Question> questionList = new ArrayList<>();
        List<QuestionOption> optionList = new ArrayList<>();

        Question question = null;
        QuestionOption option = null;
        StringBuilder optionStrBuilder = null;

        for (int questionOrder = 0; questionOrder < questions.size(); questionOrder++) {

            question = new Question();
            question.setQuestionContext(questions.get(questionOrder).getQuestionContext());
            question.setQuestionDescription(questions.get(questionOrder).getQuestionDescription());
            question.setIsMust(questions.get(questionOrder).getMust());
            question.setQuestionType(questions.get(questionOrder).getQuestionType());

            /**需要进行数据库操作，有数据库返回的自增值或者已有值来完成赋值*/
            //question.setOptionId();
            //question.setQuestionId();

            List<QuestionOptionDO> options = questions.get(questionOrder).getOptions();
            int optionSize = options.size();

            optionStrBuilder = new StringBuilder();
            for (int optionOrder = 0; optionOrder < optionSize; optionOrder++) {
                optionStrBuilder.append(options.get(optionOrder).getOption());
                if (optionSize != optionOrder + 1) {
                    //是否需要根据题目类型采用不同的切割符号？？？
                    optionStrBuilder.append(OptionStrDivideEnum.DIVIDE_SYMBOL.getDivider());
                }
            }

            option = new QuestionOption();
            option.setOptionString(optionStrBuilder.toString());

            /**需要进行数据库操作，有数据库返回的自增值或者已有值来完成赋值*/
            //option.setOptionId();

            questionList.add(question);
            optionList.add(option);
        }

        return new QuestionDTO(questionList, optionList);
    }

}
