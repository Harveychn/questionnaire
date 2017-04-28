package com.questionnaire.ssm.module.global.controller;

import com.questionnaire.ssm.module.global.pojo.UnitInfoVO;
import com.questionnaire.ssm.module.global.service.UnitService;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/26.
 * Description: 单位信息查询
 */
@Controller
@RequestMapping(value = "/unit")
public class UnitController {

    /**
     * 获取单位相关信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/listUnitInfo")
    @ResponseBody
    public List<UnitInfoVO> listUnitInfo() throws Exception {
        UserValidationUtil.checkUserValid(logger);
        return unitService.listUnitInfo();
    }

    private UnitService unitService;
    private final static Logger logger = LoggerFactory.getLogger(UnitController.class);

    @Autowired
    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }
}
