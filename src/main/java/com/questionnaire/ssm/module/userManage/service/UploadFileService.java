package com.questionnaire.ssm.module.userManage.service;

import com.questionnaire.ssm.module.userManage.pojo.UploadResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/16.
 * Description:
 */
public interface UploadFileService {
    List<UploadResultVO> uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
