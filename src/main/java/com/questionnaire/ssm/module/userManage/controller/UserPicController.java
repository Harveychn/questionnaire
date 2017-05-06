package com.questionnaire.ssm.module.userManage.controller;

import com.questionnaire.ssm.module.global.constant.CONSTANT;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;

/**
 * Created by 郑晓辉 on 2017/5/4.
 * Description:
 */
@Controller
@RequestMapping(value = "/userPic")
public class UserPicController {

    @GetMapping("/getUserPic")
    public ResponseEntity<byte[]> getUserPic(String userPicAddr) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        String filePath = null;
        if (userPicAddr == null) {
            userPicAddr = CONSTANT.getUserDefaultPicture();
        }
        filePath = CONSTANT.getUserPicturePath() + "\\" + userPicAddr.trim();
        File file = new File(filePath);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String charset = new String(userPicAddr.getBytes("utf-8"), "iso-8859-1");
        headers.setContentDispositionFormData("file", charset);
        byte[] fileByteArray = new byte[0];
        try {
            fileByteArray = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            fileByteArray = FileUtils.readFileToByteArray(
                    new File(CONSTANT.getUserPicturePath() + "\\" + CONSTANT.getUserDefaultPicture()));
        }
        return new ResponseEntity<byte[]>(fileByteArray, headers, HttpStatus.CREATED);
    }
}
