package com.questionnaire.ssm.module.resultAnalysis.controller;

import com.questionnaire.ssm.module.resultAnalysis.pojo.ListPrimaryDataInfoVO;
import com.questionnaire.ssm.module.resultAnalysis.service.PrimaryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView getPrimaryData() throws Exception{
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("resultAnalysis/primaryData");
        return modelAndView;

    }
    @GetMapping(value = "/getPrimaryDataTwo")
    public ModelAndView getPrimaryDataTwo() throws Exception{
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("resultAnalysis/primaryData_Two");
        return modelAndView;
    }
    /**
     * 获取原始数据
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value ="/listPrimaryData")
    @ResponseBody
    public List<ListPrimaryDataInfoVO> listPrimaryData()throws Exception{
        return primaryDataService.listPrimaryData();
    }

    private PrimaryDataService primaryDataService;
    @Autowired
    public ResultAnalysisController(PrimaryDataService primaryDataService){
        this.primaryDataService=primaryDataService;
    }
}
