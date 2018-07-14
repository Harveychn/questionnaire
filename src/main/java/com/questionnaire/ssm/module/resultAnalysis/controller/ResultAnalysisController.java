package com.questionnaire.ssm.module.resultAnalysis.controller;

import com.questionnaire.ssm.module.questionnaireManage.controller.IsOutOfIndex;
import com.questionnaire.ssm.module.questionnaireManage.pojo.PreOrNextQes;
import com.questionnaire.ssm.module.resultAnalysis.pojo.*;
import com.questionnaire.ssm.module.resultAnalysis.service.OriginDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 95884 on 2017/5/5.
 */
@Controller
@RequestMapping("/resultAnalysis")
public class ResultAnalysisController extends IsOutOfIndex {
    /**
     * 获取原始数据的视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getPrimaryDataView")
    public ModelAndView getPrimaryData() throws Exception {
        return new ModelAndView("resultAnalysis/originData");
    }

    /**
     * 获取原始数据的第二层视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getPrimaryDataTwo")
    public ModelAndView getPrimaryDataTwo(Long missionId, Long qesId) throws Exception {
        this.missionId = missionId;
        this.qesId = qesId;
        return new ModelAndView("resultAnalysis/originData-paperList");
    }

    /**
     * 获取某张答卷的详细内容
     *
     * @param answerPaperId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getAnswerPaperInfo")
    public ModelAndView getAnswerPaperInfo(Long answerPaperId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("resultAnalysis/displayAnswerPaper");
        DisplayAnswerPaperVO displayAnswerPaperVO = originDataService.getAnswerPaper(this.qesId, answerPaperId);
        if (displayAnswerPaperVO != null) {
            modelAndView.addObject("displayAnswerPaperVO", AnswerQesVO.translate(displayAnswerPaperVO));
            modelAndView.addObject("noError", true);
        } else {
            modelAndView.addObject("noError", false);
        }
        //当前查看问卷的id
        preOrNextQes.setCurrentQesPaperId(answerPaperId);
        isOutOfMinIndex(preOrNextQes, modelAndView);
        isOutOfMaxIndex(preOrNextQes, modelAndView);
        return modelAndView;
    }

    /**
     * 上一份答卷信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/prevAnswerPaper")
    public ModelAndView displayPrevAnswerPaper() throws Exception {
        ModelAndView modelAndView = new ModelAndView("resultAnalysis/displayAnswerPaper");
        Long displayingQesId = preOrNextQes.getPreviousQesPaperId();
        DisplayAnswerPaperVO displayAnswerPaperVO = null;
        //超出左边界
        if (displayingQesId == PreOrNextQes.OUT_OF_INDEX) {
            displayAnswerPaperVO = originDataService.getAnswerPaper(this.qesId, preOrNextQes.getCurrentQesPaperId());
            if (displayAnswerPaperVO != null) {
                modelAndView.addObject("displayAnswerPaperVO", displayAnswerPaperVO);
                modelAndView.addObject("noError", true);
            } else {
                modelAndView.addObject("noError", false);
            }
            modelAndView.addObject("isOutOfMinIndex", true);
        } else {
            displayAnswerPaperVO = originDataService.getAnswerPaper(this.qesId, displayingQesId);
            if (displayAnswerPaperVO != null) {
                modelAndView.addObject("displayAnswerPaperVO", displayAnswerPaperVO);
                modelAndView.addObject("noError", true);
            } else {
                modelAndView.addObject("noError", false);
            }
            //设置当前问卷为下一份问卷id
            preOrNextQes.setCurrentQesPaperId(displayingQesId);
            isOutOfMinIndex(preOrNextQes, modelAndView);
        }
        //判断右边界是否超出
        isOutOfMaxIndex(preOrNextQes, modelAndView);
        return modelAndView;
    }

    /**
     * 下一份答卷信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/nextAnswerPaper")
    public ModelAndView displayNextAnswerPaper() throws Exception {
        ModelAndView modelAndView = new ModelAndView("resultAnalysis/displayAnswerPaper");
        //获取下一份问卷id
        DisplayAnswerPaperVO displayAnswerPaperVO = null;
        Long displayingQesId = preOrNextQes.getNextQesPaperId();
        if (displayingQesId == PreOrNextQes.OUT_OF_INDEX) {        //没有下一份问卷
            displayAnswerPaperVO = originDataService.getAnswerPaper(this.qesId, preOrNextQes.getCurrentQesPaperId());
            if (displayAnswerPaperVO != null) {
                modelAndView.addObject("displayAnswerPaperVO", displayAnswerPaperVO);
                modelAndView.addObject("noError", true);
            } else {
                modelAndView.addObject("noError", false);
            }
            modelAndView.addObject("isOutOfMaxIndex", true);
        } else { //有下一份问卷
            displayAnswerPaperVO = originDataService.getAnswerPaper(this.qesId, displayingQesId);
            if (displayAnswerPaperVO != null) {
                modelAndView.addObject("displayAnswerPaperVO", displayAnswerPaperVO);
                modelAndView.addObject("noError", true);
            } else {
                modelAndView.addObject("noError", false);
            }
            //设置当前问卷为下一份问卷id
            preOrNextQes.setCurrentQesPaperId(displayingQesId);
            isOutOfMaxIndex(preOrNextQes, modelAndView);
        }
        //左边界判断是否超出
        isOutOfMinIndex(preOrNextQes, modelAndView);
        return modelAndView;
    }

    /**
     * 获取问卷列表
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/listPrimaryData")
    @ResponseBody
    public List<OriginDataInfoVO> listPrimaryData() throws Exception {
        return originDataService.listPrimaryData();
    }

    /**
     * 获取某次任务的答卷列表
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/listAnswerPaper")
    @ResponseBody
    public List<AnswerPaperVO> listAnswerPaper() throws Exception {
        MissionQuestionnaireVO missionQuestionnaire = new MissionQuestionnaireVO();
        missionQuestionnaire.setMissionId(this.missionId);
        missionQuestionnaire.setQuestionnaireId(this.qesId);
        List<AnswerPaperVO> result = originDataService.listAnswerPaper(missionQuestionnaire);
        int len = result.size();
        Long[] ids = new Long[len];
        for (int i = 0; i < len; i++) {
            ids[i] = result.get(i).getAnswerPaperId();
        }
        preOrNextQes = null;
        //问卷查看管理
        preOrNextQes = new PreOrNextQes(ids);
        return result;
    }

    private Long missionId;
    private Long qesId;
    private OriginDataService originDataService;
    private PreOrNextQes preOrNextQes;

    @Autowired
    public ResultAnalysisController(OriginDataService originDataService) {
        this.originDataService = originDataService;
        this.preOrNextQes = null;
    }
}
