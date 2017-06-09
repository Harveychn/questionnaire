package com.questionnaire.ssm.module.resultAnalysis.controller;

import com.questionnaire.ssm.module.resultAnalysis.pojo.ListAnswerPaperVO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.ListPrimaryDataInfoVO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.MissionQuestionnaireVO;
import com.questionnaire.ssm.module.resultAnalysis.service.PrimaryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 95884 on 2017/5/5.
 */
@Controller
@RequestMapping("/resultAnalysis")
public class ResultAnalysisController {
    /**
     * 获取原始数据的视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getPrimaryDataView")
    public ModelAndView getPrimaryData() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resultAnalysis/primaryData");
        return modelAndView;
    }

    /**
     * 获取原始数据的第二层视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getPrimaryDataTwo")
    public ModelAndView getPrimaryDataTwo(Long missionId, Long qesId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        this.missionId = missionId;
        this.qesId = qesId;
        modelAndView.setViewName("resultAnalysis/primaryDataTwo");
        return modelAndView;
    }

    /**
     * 获取答卷的详细内容
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getAnswerPaperInfo")
    public ModelAndView getAnswerPaperInfo(Long answerPaperId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("displayAnswerPaperVO", primaryDataService.getAnswerPaper(answerPaperId));
        modelAndView.setViewName("resultAnalysis/displayAnswerPaper");
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
    public List<ListPrimaryDataInfoVO> listPrimaryData() throws Exception {
        return primaryDataService.listPrimaryData();
    }

    /**
     * 获取答卷列表
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/listAnswerPaper")
    @ResponseBody
    public List<ListAnswerPaperVO> listAnswerPaper() throws Exception {
        MissionQuestionnaireVO missionQuestionnaire = new MissionQuestionnaireVO();
        missionQuestionnaire.setMissionId(this.missionId);
        missionQuestionnaire.setQuestionnaireId(this.qesId);
//        System.out.println(missionQuestionnaire.getMissionId() + "||" +missionQuestionnaire.getQuestionnaireId());
        return primaryDataService.listAnswerPaper(missionQuestionnaire);
    }

    private PrimaryDataService primaryDataService;
    private Long missionId;
    private Long qesId;

    @Autowired
    public ResultAnalysisController(PrimaryDataService primaryDataService) {
        this.primaryDataService = primaryDataService;
    }
}
