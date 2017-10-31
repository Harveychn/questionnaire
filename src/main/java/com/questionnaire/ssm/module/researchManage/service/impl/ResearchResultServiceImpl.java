package com.questionnaire.ssm.module.researchManage.service.impl;

import com.questionnaire.ssm.module.generated.pojo.AnswerDetail;
import com.questionnaire.ssm.module.generated.pojo.AnswerPaper;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.questionnaireManage.util.QesManageVODOUtil;
import com.questionnaire.ssm.module.researchManage.mapper.ResearchResultMapper;
import com.questionnaire.ssm.module.researchManage.pojo.AnswerDetailVO;
import com.questionnaire.ssm.module.researchManage.pojo.AnswerPaperVO;
import com.questionnaire.ssm.module.researchManage.service.ResearchResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by 郑晓辉 on 2017/5/3.
 * Description: 调查结果控制器
 */
@Service
public class ResearchResultServiceImpl implements ResearchResultService {

    @Override
    @Transactional
    public int multiSubmitAnswerPaper(String userTel, List<AnswerPaperVO> answerPaperVOList) throws Exception {
        List<AnswerPaper> answerPaperDOList = new ArrayList<>();
        Map<Integer, List<AnswerDetail>> answerDetailDOMap = new HashMap<>();
        List<AnswerDetail> curPaperDetailDOList = null;
        AnswerPaper answerPaper = null;
        AnswerDetail answerDetail = null;
        List<AnswerDetailVO> currentAnswerDetailVOList = null;
        AnswerPaperVO currentAnswerPaperVO = null;

        for (int i = 0; i < answerPaperVOList.size(); i++) {
            currentAnswerPaperVO = answerPaperVOList.get(i);
            answerPaper = new AnswerPaper();
            //转移VO数据到DO类
            //设置提交时间
            answerPaper.setSubmitTime(new Date());
            //是否提交设置,永远设置为提交
            answerPaper.setIsSubmit(Byte.valueOf("1"));
            //外键相关设置
            answerPaper.setQuestionnaireId(currentAnswerPaperVO.getQuestionnaireId());
            /*此处设置当前登陆用户名*/
            answerPaper.setSubmitUserTel(userTel);
            answerPaper.setMissionId(currentAnswerPaperVO.getResearchId());
            answerPaper.setLongitude(BigDecimal.valueOf(currentAnswerPaperVO.getLongitude()));
            answerPaper.setLatitude(BigDecimal.valueOf(currentAnswerPaperVO.getLatitude()));
            answerPaper.setFillAnswerTime(currentAnswerPaperVO.getFillAnswerTime());

            answerPaperDOList.add(answerPaper);

            currentAnswerDetailVOList = currentAnswerPaperVO.getAnswerDetailVOList();
            if (currentAnswerDetailVOList.size() > 0) {

                curPaperDetailDOList = new ArrayList<>();
                //答卷id均未设置，在答卷信息保存后设置
                for (AnswerDetailVO currentAnswerDetailVO : currentAnswerDetailVOList) {
                    //答卷id未设置
                    answerDetail = QesManageVODOUtil.toAnswerDetailDO(currentAnswerDetailVO);
                    curPaperDetailDOList.add(answerDetail);
                }
                answerDetailDOMap.put(i, curPaperDetailDOList);
            }
        }

        //批量插入数据到answerPaper
        int operateSuccessCount = 0;
        try {
            operateSuccessCount = researchResultMapper.insert2AnswerPaperBatch(answerPaperDOList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL, "上传答卷失败!（插入信息到答卷表失败）");
        }

        //批量插入数据到answerPaperDetail
        AnswerPaper answerPaperWithId = null;
        List<AnswerDetail> answerDetailsWithoutAnswerPaperId = null;
        for (int i = 0; i < answerPaperDOList.size(); i++) {
            answerPaperWithId = answerPaperDOList.get(i);
            answerDetailsWithoutAnswerPaperId = answerDetailDOMap.get(i);
            for (AnswerDetail currentDetail : answerDetailsWithoutAnswerPaperId) {
                currentDetail.setAnswerPaperId(answerPaperWithId.getAnswerPaperId());
            }
        }

        for (Integer index : answerDetailDOMap.keySet()) {
            try {
                researchResultMapper.insert2AnswerDetailBatch(answerDetailDOMap.get(index));
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL, "上传答卷失败!（插入信息到答卷详细信息表失败）");
            }
        }
        return operateSuccessCount;
    }

    private ResearchResultMapper researchResultMapper;
    private final static Logger logger = LoggerFactory.getLogger(ResearchResultServiceImpl.class);

    @Autowired
    public ResearchResultServiceImpl(ResearchResultMapper researchResultMapper) {
        this.researchResultMapper = researchResultMapper;
    }
}
