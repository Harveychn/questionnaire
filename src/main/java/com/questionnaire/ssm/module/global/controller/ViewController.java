package com.questionnaire.ssm.module.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by 郑晓辉 on 2017/4/9.
 * Description: 全局页面获取控制器
 */
@Controller
public class ViewController {

    @GetMapping(value = "/home")
    public String homePage() throws Exception {
        return "home";
    }
}
