package com.questionnaire.ssm.module.userManage.controller;

import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.CheckUploadFileUtil;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.userManage.pojo.CheckUploadFileDTO;
import com.questionnaire.ssm.module.userManage.pojo.UploadResultVO;
import com.questionnaire.ssm.module.userManage.service.UploadFileService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/16.
 * Description: 用户管理，包括用户信息数据上传、单位信息上传
 */
@Controller
@RequestMapping("/userManage")
public class UserManageController {

    @GetMapping(value = "/getUploadView")
    public ModelAndView getUploadView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload/uploadExcel");
        return modelAndView;
    }

    @PostMapping(value = "/uploadData")
    @ResponseBody
    public ResponsePkt uploadData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<UploadResultVO> resultVOList = uploadFileService.uploadFile(request, response);
        this.uploadResultVOList = resultVOList;
        return ResultUtil.success(resultVOList);
    }

    @GetMapping(value = "/uploadResultView")
    @ResponseBody
    public ModelAndView uploadResultView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload/uploadResult");
        if (this.uploadResultVOList != null) {
            modelAndView.addObject("uploadResultVOList", this.uploadResultVOList);
        }
        return modelAndView;
    }

    /**
     * 下载 上传模板
     *
     * @param templateName 要下载的模板名
     * @param response
     * @throws IOException
     */
    @GetMapping("/downloadUploadTemplate")
    public void downloadUploadTemplate(String templateName, HttpServletResponse response) throws IOException {
        String fileOriginName = templateName + ".xls";
        String path = CONSTANT.getDownloadFolderPath() + "\\" + fileOriginName;
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("utf-8");
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String((templateName + ".xls").getBytes(), "iso-8859-1"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }


    private List<UploadResultVO> uploadResultVOList;
    private final static Logger logger = LoggerFactory.getLogger(UserManageController.class);
    private UploadFileService uploadFileService;

    @Autowired
    public UserManageController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }
}
