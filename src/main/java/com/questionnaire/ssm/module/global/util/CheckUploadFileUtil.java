package com.questionnaire.ssm.module.global.util;

import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.userManage.pojo.CheckUploadFileDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/16.
 * Description: 检查上传文件的格式
 */
public class CheckUploadFileUtil {

    public static List<CheckUploadFileDTO> checkUploadFiles(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
        List<MultipartFile> fileList = mr.getFiles("files[]");

        List<CheckUploadFileDTO> checkUploadFileDTOList = new ArrayList<>();
        CheckUploadFileDTO currentDTO = null;

        String fileOriginName = null;
        SimpleDateFormat folderSdf = new SimpleDateFormat("yyyy-MM-dd");
        String folderPath = null;
        SimpleDateFormat fileSdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String tempFilePath = null;

        for (MultipartFile multipartFile : fileList) {
            currentDTO = new CheckUploadFileDTO();
            fileOriginName = multipartFile.getOriginalFilename();
            currentDTO.setOriginFileName(fileOriginName);
            //文件类型错误
            if (!isExcelFileType(fileOriginName)) {
                currentDTO.setFileTypeError(true);
                checkUploadFileDTOList.add(currentDTO);
                continue;
            }
            //文件类型没错
            folderPath = CONSTANT.getUploadFilePath() + "/" + folderSdf.format(new Date());
            File fileFolder = new File(folderPath);
            //文件目录不存在则创建目录
            if (!fileFolder.exists()) {
                fileFolder.mkdirs();
            }

            tempFilePath = folderPath + "/" + fileSdf.format(new Date()) + "-" + fileOriginName;
            File file = new File(tempFilePath);

            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                logger.error(e.getMessage());
                currentDTO.setFileTransError(true);
                checkUploadFileDTOList.add(currentDTO);
                continue;
            } catch (Exception e) {
                logger.error(e.getMessage());
                currentDTO.setFileTransError(true);
                checkUploadFileDTOList.add(currentDTO);
                continue;
            }
            currentDTO.setFileTransError(false);
            currentDTO.setFileTypeError(false);
            currentDTO.setTempFilePath(tempFilePath);
            checkUploadFileDTOList.add(currentDTO);
        }
        return checkUploadFileDTOList;
    }

    /**
     * 删除文件
     *
     * @param tempFilePath 要删除文件的绝对路径
     * @return
     */
    public static boolean deleteTempFile(String tempFilePath) {
        boolean flag = false;
        File file = new File(tempFilePath);
        try {
            if (file.isFile() && file.exists()) {
                file.delete();
                flag = true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return flag;
    }

    /**
     * 检查上传的文件是否excel表格
     *
     * @param fileName 上传的文件完整名
     * @return
     */
    private static boolean isExcelFileType(String fileName) {
        boolean flag = false;
        String suffixList = "xls,xlsx";
        //获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

        if (suffixList.contains(suffix.trim().toLowerCase())) {
            flag = true;
        }
        return flag;
    }

    private final static Logger logger = LoggerFactory.getLogger(CheckUploadFileUtil.class);
}
