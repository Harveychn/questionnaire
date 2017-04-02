package com.questionnaire.ssm.module.questionnaireManage.util;

import com.questionnaire.ssm.module.questionnaireManage.pojo.CreateQuestionnaireVO;

/**
 * Created by 郑晓辉 on 2017/3/24.
 * Description:校验前台传输数据有效的工具类
 */
public class CheckVOValidUtil {

    /**
     * 校验创建问卷的VO实体
     * 问卷标题不得为空
     *
     * @param questionnaireVO
     * @return
     * @throws Exception
     */
    public static boolean createQuestionnaireVOValid(CreateQuestionnaireVO questionnaireVO) throws Exception {
        if (null == questionnaireVO.getQuestionnaireTitle()
                || "".equals(questionnaireVO.getQuestionnaireTitle().trim())) {
            return false;
        }
        return true;
    }
}
