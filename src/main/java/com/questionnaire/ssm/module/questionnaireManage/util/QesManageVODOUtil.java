package com.questionnaire.ssm.module.questionnaireManage.util;

import com.questionnaire.ssm.module.generated.pojo.AnswerDetail;
import com.questionnaire.ssm.module.generated.pojo.QuestionWithBLOBs;
import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.exception.UserValidaException;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.questionnaireManage.enums.QuestionTypeEnum;
import com.questionnaire.ssm.module.questionnaireManage.pojo.*;
import com.questionnaire.ssm.module.researchManage.pojo.AnswerDetailVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:用于前台VO实体与后台数据库实体类相互转换的工具类
 */
public class QesManageVODOUtil {

    /**
     * 提取创建问卷视图中实体数据到数据库问卷实体
     *
     * @param questionnaireVO 问卷信息
     * @return
     * @throws Exception
     */
    public static Questionnaire toQuestionnaireDO(CreateQesVO questionnaireVO) throws Exception {
        Questionnaire questionnaire = new Questionnaire();

        questionnaire.setQuestionnaireTitle(questionnaireVO.getQuestionnaireTitle());
        questionnaire.setQuestionnaireSubtitle(questionnaireVO.getQuestionnaireSubtitle());
        questionnaire.setQuestionnaireDescription(questionnaireVO.getQuestionnaireDescription());
        questionnaire.setIsDone(questionnaireVO.getDone());
        questionnaire.setIsTemplate(questionnaireVO.getTemplate());

        questionnaire.setIsShare(false);
        questionnaire.setIsVisible(true);
        questionnaire.setIsRelease(false);

        //创建问卷时间
        questionnaire.setCreateTime(new Date());
        try {
            questionnaire.setCreateUser(UserValidationUtil.getUserTel(logger));
        } catch (NullPointerException e) {
            throw new UserValidaException(CodeForVOEnum.NOT_LOGIN);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new UserValidaException(CodeForVOEnum.UNKNOWN_ERROR);
        }

        return questionnaire;
    }

    /**
     * 将数据库中问卷实体的数据包装到 展示问卷视图的实体中去
     *
     * @param questionnaireDO
     * @return
     * @throws Exception
     */
    public static DisplayQesVO toDisplayQuestionnaireVO(Questionnaire questionnaireDO)
            throws Exception {
        DisplayQesVO displayQesVO = new DisplayQesVO();

        displayQesVO.setQuestionnaireId(questionnaireDO.getQuestionnaireId());

        if (questionnaireDO.getQuestionnaireTitle() != null) {
            displayQesVO.setQuestionnaireTitle(questionnaireDO.getQuestionnaireTitle());
        }
        if (questionnaireDO.getQuestionnaireSubtitle() != null) {
            displayQesVO.setQuestionnaireSubtitle(questionnaireDO.getQuestionnaireSubtitle());
        }
        if (questionnaireDO.getQuestionnaireDescription() != null) {
            displayQesVO.setQuestionnaireDescription(questionnaireDO.getQuestionnaireDescription());
        }
        return displayQesVO;
    }

    /**
     * 将数据库中问题数据包装到 展示问卷视图的实体中
     *
     * @param question
     * @return
     * @throws Exception
     */
    public static QuestionVO toQuestionVO(QuestionWithBLOBs question, List<QuestionOptionVO> questionOptionVOList) throws Exception {
        QuestionVO questionVO = new QuestionVO();

        //用于上传答卷信息时保存数据使用
        questionVO.setQuestionId(question.getQuestionId());

        if (question.getQuestionContext() != null) {
            questionVO.setQuestionContext(question.getQuestionContext());
        }
        if (question.getQuestionDescription() != null) {
            questionVO.setQuestionDescription(question.getQuestionDescription());
        }
        if (questionOptionVOList.size() > 0) {
            questionVO.setOptions(questionOptionVOList);
        }
        //问题后跟选项
        if (question.getOptionFollow() != null) {
            questionVO.setQuestionFollow(question.getQuestionFollow());
        } else {
            questionVO.setQuestionFollow(CONSTANT.NO_FOLLOW_DEFAULT_VALUE);
        }
        /*数据库中不为空字段*/
        questionVO.setQuestionType(parse2VOQuestionType(question.getQuestionType()));
        questionVO.setMust(question.getIsMust());
        return questionVO;
    }

    /**
     * 提取问卷中题目的题目信息以及题目的选项数据信息
     *
     * @param voQuestions 问卷题目信息
     * @return
     * @throws Exception
     */
    public static List<QuestionWithBLOBs> toQuestionMultiDO(List<QuestionVO> voQuestions) throws Exception {
        List<QuestionWithBLOBs> questionWithBLOBsList = new ArrayList<>();

        QuestionWithBLOBs qesItem = null;

        for (QuestionVO iteVOQes : voQuestions) {

            qesItem = new QuestionWithBLOBs();
            qesItem.setQuestionContext(iteVOQes.getQuestionContext());
            qesItem.setQuestionDescription(iteVOQes.getQuestionDescription());
            //获取 ‘转到问题’ 在当前问卷实体中的数组编号 此处注意 数组编号从0 开始，故前台为填写时的默认值为:-1
            qesItem.setQuestionFollow(iteVOQes.getQuestionFollow());
            qesItem.setIsMust(iteVOQes.getMust());
            /*需要将汉字问题类型装换为数据库中的类型编码*/
            qesItem.setQuestionType(parse2DOQuestionType(iteVOQes.getQuestionType()));

            toOptionDOItem(qesItem, iteVOQes.getOptions(), qesItem.getQuestionType());

            questionWithBLOBsList.add(qesItem);
        }

        return questionWithBLOBsList;
    }

    /**
     * 获取答案详细数据库实体类
     *
     * @param answerDetailVO 视图中答案详细实体
     * @return
     * @throws Exception
     */
    public static AnswerDetail toAnswerDetailDO(AnswerDetailVO answerDetailVO) throws Exception {
        AnswerDetail answerDetail = new AnswerDetail();
        answerDetail.setQuestionId(answerDetailVO.getQuestionId());
        //获取汉字形式文本的数据库表示值
        String questionCode = parse2DOQuestionType(answerDetailVO.getQuestionType());
        if (answerDetailVO.getAnswer().size() > 0) {
            answerDetail.setAnswerString(toAnswerString(answerDetailVO.getAnswer(), questionCode));
        } else {
            //该题用户未作答，默认设置为‘未回答’
            answerDetail.setAnswerString(CONSTANT.getNullAnswerString());
        }
        return answerDetail;
    }

    /**
     * 将数据库中的问题类型代码转化为 汉字表达形式
     *
     * @param typeCode 数据库中的存储代码
     * @return
     * @throws Exception
     */
    public static String parse2VOQuestionType(String typeCode) throws Exception {
        typeCode = typeCode.trim();
        if (QuestionTypeEnum.SINGLE_LINE_BLANK.getCode().equals(typeCode)) {
            return QuestionTypeEnum.SINGLE_LINE_BLANK.getQuestionType();
        }
        if (QuestionTypeEnum.MULTI_LINE_BLANK.getCode().equals(typeCode)) {
            return QuestionTypeEnum.MULTI_LINE_BLANK.getQuestionType();
        }
        if (QuestionTypeEnum.SINGLE_CHOICE.getCode().equals(typeCode)) {
            return QuestionTypeEnum.SINGLE_CHOICE.getQuestionType();
        }
        if (QuestionTypeEnum.MULTIPLE_CHOICE.getCode().equals(typeCode)) {
            return QuestionTypeEnum.MULTIPLE_CHOICE.getQuestionType();
        }
        if (QuestionTypeEnum.DROP_SELECTION.getCode().equals(typeCode)) {
            return QuestionTypeEnum.DROP_SELECTION.getQuestionType();
        }
        if (QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getCode().equals(typeCode)) {
            return QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getQuestionType();
        }
        if (QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getCode().equals(typeCode)) {
            return QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getQuestionType();
        }
        if (QuestionTypeEnum.SHORT_ANSWER.getCode().equals(typeCode)) {
            return QuestionTypeEnum.SHORT_ANSWER.getQuestionType();
        }
        if (QuestionTypeEnum.TIME_POINT.getCode().equals(typeCode)) {
            return QuestionTypeEnum.TIME_POINT.getQuestionType();
        }
        return QuestionTypeEnum.UNKNOWN_TYPE.getQuestionType();
    }

    /**
     * 转换汉字问题类型文本到数据库问题类型代码
     *
     * @param typeString
     * @return
     * @throws Exception
     */
    private static String parse2DOQuestionType(String typeString) throws Exception {
        typeString = typeString.trim();
        if (QuestionTypeEnum.SINGLE_LINE_BLANK.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.SINGLE_LINE_BLANK.getCode();
        }
        if (QuestionTypeEnum.MULTI_LINE_BLANK.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.MULTI_LINE_BLANK.getCode();
        }
        if (QuestionTypeEnum.SINGLE_CHOICE.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.SINGLE_CHOICE.getCode();
        }
        if (QuestionTypeEnum.MULTIPLE_CHOICE.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.MULTIPLE_CHOICE.getCode();
        }
        if (QuestionTypeEnum.DROP_SELECTION.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.DROP_SELECTION.getCode();
        }
        if (QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getCode();
        }
        if (QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getCode();
        }
        if (QuestionTypeEnum.SHORT_ANSWER.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.SHORT_ANSWER.getCode();
        }
        if (QuestionTypeEnum.TIME_POINT.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.TIME_POINT.getCode();
        }
        return QuestionTypeEnum.UNKNOWN_TYPE.getCode();
    }


    /**
     * 将optionVO实体集封装到后台数据库实体中
     *
     * @param optionDOItem 后台存储实体集
     * @param optionVOList 前端数据信息
     * @param qesType      问题类型
     */
    //TODO 未测试是否正常效果
    private static void toOptionDOItem(QuestionWithBLOBs optionDOItem, List<QuestionOptionVO> optionVOList, String qesType) {
        int optionSize = optionVOList.size();
        StringBuilder optionStrBuilder = new StringBuilder();
        StringBuilder opFollowBuilder = new StringBuilder();
        for (int optionOrder = 0; optionOrder < optionSize; optionOrder++) {

            optionStrBuilder.append(optionVOList.get(optionOrder).getOption());
            opFollowBuilder.append(optionVOList.get(optionOrder).getOptionFollow());

            if (optionSize != optionOrder + 1) {
                //需要根据题目类型采用不同的切割符号
                if (qesType.equals(QuestionTypeEnum.SINGLE_CHOICE.getCode())
                        || qesType.equals(QuestionTypeEnum.MULTIPLE_CHOICE.getCode())
                        || qesType.equals(QuestionTypeEnum.SINGLE_LINE_BLANK.getCode())
                        || qesType.equals(QuestionTypeEnum.MULTI_LINE_BLANK.getCode())
                        || qesType.equals(QuestionTypeEnum.DROP_SELECTION.getCode())
                        || qesType.equals(QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getCode())
                        || qesType.equals(QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getCode())
                        || qesType.equals(QuestionTypeEnum.TIME_POINT.getCode())) {

                    optionStrBuilder.append(QuestionTypeEnum.SINGLE_CHOICE.getDivideStr());
                    opFollowBuilder.append(QuestionTypeEnum.SINGLE_CHOICE.getDivideStr());
                }
            }
        }
        optionDOItem.setOptionString(optionStrBuilder.toString());
        optionDOItem.setOptionFollow(opFollowBuilder.toString());
    }

    /**
     * 将选项条目转换为选项条目字符串
     *
     * @param optionVOList     VO视图的问题选项
     * @param questionTypeCode 数据库中问题类型编码
     * @return
     * @throws Exception
     */
    private static String toOptionsString(List<QuestionOptionVO> optionVOList, String questionTypeCode) {
        int optionSize = optionVOList.size();
        StringBuilder optionStrBuilder = new StringBuilder();
        for (int optionOrder = 0; optionOrder < optionSize; optionOrder++) {
            optionStrBuilder.append(optionVOList.get(optionOrder).getOption());
            if (optionSize != optionOrder + 1) {
                //需要根据题目类型采用不同的切割符号
                if (questionTypeCode.equals(QuestionTypeEnum.SINGLE_CHOICE.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.MULTIPLE_CHOICE.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.SINGLE_LINE_BLANK.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.MULTI_LINE_BLANK.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.DROP_SELECTION.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getCode())) {
                    optionStrBuilder.append(QuestionTypeEnum.SINGLE_CHOICE.getDivideStr());
                }
            }
        }
        return optionStrBuilder.toString();
    }

    /**
     * 将答卷中的答案装为 答案String类型
     *
     * @param answerVOStringList "视图"中答卷答案文本
     * @param questionTypeCode   数据库中问题类型编码
     * @return
     * @throws Exception
     */
    public static String toAnswerString(List<String> answerVOStringList, String questionTypeCode) throws Exception {
        int optionSize = answerVOStringList.size();
        StringBuilder optionStrBuilder = new StringBuilder();
        for (int optionOrder = 0; optionOrder < optionSize; optionOrder++) {
            optionStrBuilder.append(answerVOStringList.get(optionOrder));
            if (optionSize != optionOrder + 1) {
                //需要根据题目类型采用不同的切割符号
                if (questionTypeCode.equals(QuestionTypeEnum.SINGLE_CHOICE.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.MULTIPLE_CHOICE.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.SINGLE_LINE_BLANK.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.MULTI_LINE_BLANK.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.DROP_SELECTION.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.TIME_POINT.getCode())) {
                    optionStrBuilder.append(QuestionTypeEnum.SINGLE_CHOICE.getDivideStr());
                }
            }
        }
        return optionStrBuilder.toString();
    }

    /**
     * 将数据库选项字符串切割成正常的选项信息
     *
     * @param optionString
     * @param optionFollowStr 选项后跟题目
     * @return
     * @throws Exception
     */
    public static List<QuestionOptionVO> toOptionsItem(String optionString, String optionFollowStr, String questionTypeCode) throws Exception {
        List<QuestionOptionVO> questionOptionVOList = new ArrayList<>();
        QuestionOptionVO questionOptionVO = null;

        String[] options = null, optionFollow = null;
        if (questionTypeCode.equals(QuestionTypeEnum.SINGLE_CHOICE.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.MULTIPLE_CHOICE.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.SINGLE_LINE_BLANK.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.MULTI_LINE_BLANK.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.DROP_SELECTION.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.SHORT_ANSWER.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.TIME_POINT.getCode())) {
            options = optionString.split("\\|\\|");
            if (optionFollowStr != null) {
                optionFollow = optionFollowStr.split("\\|\\|");
            }
        }
        if (options == null) {
            return null;
        }

        for (int order = 0; order < options.length; order++) {
            questionOptionVO = new QuestionOptionVO();
            questionOptionVO.setOptionOrder(order);
            questionOptionVO.setOption(options[order]);
            try {
                if (optionFollow != null) {
                    questionOptionVO.setOptionFollow(Integer.valueOf(optionFollow[order]));
                } else {
                    questionOptionVO.setOptionFollow(CONSTANT.NO_FOLLOW_DEFAULT_VALUE);
                }
            } catch (NumberFormatException e) {
                questionOptionVO.setOptionFollow(CONSTANT.NO_FOLLOW_DEFAULT_VALUE);
            }
            questionOptionVOList.add(order, questionOptionVO);
        }
        return questionOptionVOList;
    }


    private final static Logger logger = LoggerFactory.getLogger(QesManageVODOUtil.class);
}
