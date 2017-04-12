package com.questionnaire.ssm.module.questionnaireManage.util;

import com.questionnaire.ssm.module.generated.pojo.MappingQuestionnaireQuestion;
import com.questionnaire.ssm.module.generated.pojo.Questionnaire;

/**
 * Created by 郑晓辉 on 2017/3/29.
 * Description: 操作问卷动作的工具包
 */
public class OperateQuestionnaireUtil {

    /**
     * 删除个人问卷到回收站
     *
     * @return
     * @throws Exception
     */
    public static Questionnaire deleteQesPaperTemporaryAction() throws Exception {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setIsTemplate(false);
        questionnaire.setIsShare(false);
        questionnaire.setIsVisible(false);
        questionnaire.setIsDone(true);
        questionnaire.setIsDelete(false);
        return questionnaire;
    }

    /**
     * 删除问卷永久
     *
     * @return
     * @throws Exception
     */
    public static Questionnaire deleteQesPaperForeverAction() throws Exception {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setIsTemplate(false);
        questionnaire.setIsShare(false);
        questionnaire.setIsVisible(false);
        questionnaire.setIsDone(true);
        questionnaire.setIsDelete(true);
        return questionnaire;
    }

    /**
     * 对被操作问卷的更新操作
     *
     * @return
     * @throws Exception
     */
    public static Questionnaire shareAction() throws Exception {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setIsShare(true);
        return questionnaire;
    }

    /**
     * 共享问卷到公共模板库
     *
     * @return
     * @throws Exception
     */
    public static Questionnaire share2PublicTemplateAction() throws Exception {
        Questionnaire questionnaire = new Questionnaire();

        questionnaire.setIsTemplate(true);
        questionnaire.setIsShare(true);
        questionnaire.setIsVisible(true);
        questionnaire.setIsDone(true);
        questionnaire.setIsDelete(false);

        return questionnaire;
    }

    /**
     * 模板化问卷
     *
     * @return
     * @throws Exception
     */
    public static Questionnaire templateQesPaperAction() throws Exception {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setIsTemplate(true);
        questionnaire.setIsShare(false);
        questionnaire.setIsVisible(true);
        questionnaire.setIsDone(true);
        questionnaire.setIsDelete(false);
        return questionnaire;
    }

    /**
     * 共享问卷到公共模板时，需要赋值一份新的问卷信息
     *
     * @param questionnaire 要复制的问卷信息
     * @return
     * @throws Exception
     */
    public static Questionnaire copyQesPaper2Public(Questionnaire questionnaire) throws Exception {
        Questionnaire copiedQuestionnaire = share2PublicTemplateAction();
        /*问卷标题不得为空*/
        copiedQuestionnaire.setQuestionnaireTitle(questionnaire.getQuestionnaireTitle());
        if (questionnaire.getQuestionnaireSubtitle() != null) {
            copiedQuestionnaire.setQuestionnaireSubtitle(questionnaire.getQuestionnaireSubtitle());
        }
        if (questionnaire.getQuestionnaireDescription() != null) {
            copiedQuestionnaire.setQuestionnaireDescription(questionnaire.getQuestionnaireDescription());
        }
        return copiedQuestionnaire;
    }

    /**
     * 从添加公共模板到个人模板库时复制信息
     *
     * @param questionnaire
     * @return
     * @throws Exception
     */
    public static Questionnaire copyQesPaperFromPublic(Questionnaire questionnaire) throws Exception {
        Questionnaire copiedQuestionnaire = new Questionnaire();

        copiedQuestionnaire.setIsTemplate(true);
        copiedQuestionnaire.setIsShare(false);
        copiedQuestionnaire.setIsVisible(true);
        copiedQuestionnaire.setIsDone(true);
        copiedQuestionnaire.setIsDelete(false);

        /*问卷标题不得为空*/
        copiedQuestionnaire.setQuestionnaireTitle(questionnaire.getQuestionnaireTitle());
        if (questionnaire.getQuestionnaireSubtitle() != null) {
            copiedQuestionnaire.setQuestionnaireSubtitle(questionnaire.getQuestionnaireSubtitle());
        }
        if (questionnaire.getQuestionnaireDescription() != null) {
            copiedQuestionnaire.setQuestionnaireDescription(questionnaire.getQuestionnaireDescription());
        }
        return copiedQuestionnaire;
    }

    /**
     * 共享问卷时，需要赋值一份问卷-问题映射关系
     *
     * @param mappingQuestionnaireQuestion 其中问卷id为新获取的问卷id
     * @return
     * @throws Exception
     */
    public static MappingQuestionnaireQuestion copyMapQesPaperQes(MappingQuestionnaireQuestion mappingQuestionnaireQuestion) throws Exception {
        MappingQuestionnaireQuestion copiedMap = new MappingQuestionnaireQuestion();
        /*问卷id未设置*/
//        copiedMap.setQuestionnaireId(mappingQuestionnaireQuestion.getQuestionnaireId());
        copiedMap.setQuestionId(mappingQuestionnaireQuestion.getQuestionId());
        copiedMap.setQuestionOrder(mappingQuestionnaireQuestion.getQuestionOrder());

        return copiedMap;
    }


}
