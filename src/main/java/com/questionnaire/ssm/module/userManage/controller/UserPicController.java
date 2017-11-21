package com.questionnaire.ssm.module.userManage.controller;

import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.global.util.DownloadPicUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by 郑晓辉 on 2017/5/4.
 * Description:
 */
@Controller
@RequestMapping(value = "/userPic")
public class UserPicController {

    @GetMapping("/getUserPic")

    public ResponseEntity<byte[]> getUserPic(String userPicAddr) throws Exception {
        return DownloadPicUtil.download(CONSTANT.getUserPicturePath(), userPicAddr);
    }
}
