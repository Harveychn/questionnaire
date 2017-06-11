package com.questionnaire.ssm.module.questionnaireManage.util;

import com.questionnaire.ssm.module.generated.pojo.MappingQuestionnaireQuestion;
import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.exception.UserValidaException;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

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
/*2017-5-21 移除问卷操作只变动is_visible =false*/
//        questionnaire.setIsTemplate(false);
//        questionnaire.setIsShare(false);
//        questionnaire.setIsVisible(false);
//        questionnaire.setIsDone(true);
//        questionnaire.setIsDelete(false);

        questionnaire.setIsVisible(false);

        return questionnaire;
    }

    /**
     * 恢复回收站问卷到个人问卷
     *
     * @return
     * @throws Exception
     */
    public static Questionnaire restoreQesPaperAction() throws Exception {
        Questionnaire questionnaire = new Questionnaire();

        /*2017-5-21恢复问卷到移除时位置*/
//        questionnaire.setIsTemplate(false);
//        questionnaire.setIsShare(false);
//        questionnaire.setIsVisible(true);
//        questionnaire.setIsDone(true);
//        questionnaire.setIsDelete(false);
        questionnaire.setIsVisible(true);
        return questionnaire;
    }

//    /**
//     * 共享问卷到公共模板库
//     *
//     * @return
//     * @throws Exception
//     */
//    public static Questionnaire share2PublicTemplateAction() throws Exception {
//        Questionnaire questionnaire = new Questionnaire();
//
//        questionnaire.setIsTemplate(true);
//        questionnaire.setIsShare(true);
//        questionnaire.setIsVisible(true);
//        questionnaire.setIsDone(true);
//        questionnaire.setIsDelete(false);
//
//        return questionnaire;
//    }

    /**
     * 模板化问卷
     *
     * @return
     * @throws Exception
     */
    public static Questionnaire templateQesPaper(Questionnaire templatingQes) throws Exception {
        Questionnaire resultQes = new Questionnaire();

        resultQes.setIsTemplate(true);
        resultQes.setIsShare(false);
        resultQes.setIsVisible(true);
        resultQes.setIsDone(true);
        resultQes.setIsRelease(false);

        /*问卷标题不得为空*/
        resultQes.setQuestionnaireTitle(templatingQes.getQuestionnaireTitle());
        if (templatingQes.getQuestionnaireSubtitle() != null) {
            resultQes.setQuestionnaireSubtitle(templatingQes.getQuestionnaireSubtitle());
        }
        if (templatingQes.getQuestionnaireDescription() != null) {
            resultQes.setQuestionnaireDescription(templatingQes.getQuestionnaireDescription());
        }
        //问卷模板化时间
        resultQes.setCreateTime(new Date());
        //模板化操作的用户
        resultQes.setCreateUser(UserValidationUtil.getUserTel(logger));

        return resultQes;
    }

    /**
     * 添加到公共模板
     * 需要赋值一份新的问卷信息
     *
     * @param questionnaire 要复制的问卷信息
     * @return
     * @throws Exception
     */
    public static Questionnaire copyQesPaper2Public(Questionnaire questionnaire) throws Exception {
        //设置属性信息
//        Questionnaire copiedQuestionnaire = share2PublicTemplateAction();
        Questionnaire copiedQuestionnaire = new Questionnaire();

        copiedQuestionnaire.setIsTemplate(true);
        copiedQuestionnaire.setIsShare(true);
        copiedQuestionnaire.setIsVisible(true);
        copiedQuestionnaire.setIsDone(true);
        copiedQuestionnaire.setIsRelease(false);

        /*标题不得为空*/
        copiedQuestionnaire.setQuestionnaireTitle(questionnaire.getQuestionnaireTitle());
        if (questionnaire.getQuestionnaireSubtitle() != null) {
            copiedQuestionnaire.setQuestionnaireSubtitle(questionnaire.getQuestionnaireSubtitle());
        }
        if (questionnaire.getQuestionnaireDescription() != null) {
            copiedQuestionnaire.setQuestionnaireDescription(questionnaire.getQuestionnaireDescription());
        }
        //分享时间
        copiedQuestionnaire.setCreateTime(new Date());
        //分享用户
        copiedQuestionnaire.setCreateUser(UserValidationUtil.getUserTel(logger));
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
        copiedQuestionnaire.setIsRelease(false);

        /*问卷标题不得为空*/
        copiedQuestionnaire.setQuestionnaireTitle(questionnaire.getQuestionnaireTitle());
        if (questionnaire.getQuestionnaireSubtitle() != null) {
            copiedQuestionnaire.setQuestionnaireSubtitle(questionnaire.getQuestionnaireSubtitle());
        }
        if (questionnaire.getQuestionnaireDescription() != null) {
            copiedQuestionnaire.setQuestionnaireDescription(questionnaire.getQuestionnaireDescription());
        }

        //添加到个人模板库时间
        copiedQuestionnaire.setCreateTime(new Date());
        //分享用户
        copiedQuestionnaire.setCreateUser(questionnaire.getCreateUser());
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
        copiedMap.setQuestionId(mappingQuestionnaireQuestion.getQuestionId());
        copiedMap.setQuestionOrder(mappingQuestionnaireQuestion.getQuestionOrder());

        return copiedMap;
    }


    private final static Logger logger = LoggerFactory.getLogger(OperateQuestionnaireUtil.class);
}
