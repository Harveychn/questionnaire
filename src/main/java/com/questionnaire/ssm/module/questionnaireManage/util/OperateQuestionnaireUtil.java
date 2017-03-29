package com.questionnaire.ssm.module.questionnaireManage.util;

import com.questionnaire.ssm.module.generated.pojo.Questionnaire;

/**
 * Created by 郑晓辉 on 2017/3/29.
 * Description: 操作问卷的工具包
 */
public class OperateQuestionnaireUtil {

    /**
     * 删除问卷暂时（visible为false）
     *
     * @return
     * @throws Exception
     */
    public static Questionnaire deleteTemporaryAction() throws Exception {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setIsVisible(false);
        return questionnaire;
    }

    /**
     * 删除问卷永久(delete 为true)
     *
     * @return
     * @throws Exception
     */
    public static Questionnaire deleteForeverAction() throws Exception {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setIsVisible(false);
        questionnaire.setIsDelete(true);
        return questionnaire;
    }

    /**
     * 分享问卷
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
     * 模板化问卷
     *
     * @return
     * @throws Exception
     */
    public static Questionnaire templateAction() throws Exception {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setIsTemplate(true);
        return questionnaire;
    }
}
