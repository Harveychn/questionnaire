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
 * Description:
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

    @GetMapping("/downloadUploadTemplate")
    public void downloadUploadTemplate(String templateName, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=download");
//        DownloadParamVo downloadParamVo = this.downloadParamVo;
//        HSSFWorkbook workbook = downloadDBDataService.downloadData(downloadParamVo);
//        Date currentDate = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
//        String fileName = sdf.format(currentDate);
//        if ("Disease".equals(downloadParamVo.getCategory().trim())) {
//            fileName += "疟疾";
//        } else if ("Weather".equals(downloadParamVo.getCategory().trim())) {
//            fileName += "气候";
//        } else if ("Station".equals(downloadParamVo.getCategory().trim())) {
//            fileName += "观测站";
//        }
        String fileOriginName = templateName + ".xlsx";
        String filePath = CONSTANT.getUploadFilePath() + "\\" + fileOriginName;
        File file = new File(filePath);
        
        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        try {
//            workbook.write(os);
//        } catch (IOException io) {
//            System.out.println("workbook.write(os)出现IOException 信息：" + io.getMessage());
//        }

        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((templateName + ".xls").getBytes(),
                "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        return;
    }


    private List<UploadResultVO> uploadResultVOList;
    private final static Logger logger = LoggerFactory.getLogger(UserManageController.class);
    private UploadFileService uploadFileService;

    @Autowired
    public UserManageController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }
}
