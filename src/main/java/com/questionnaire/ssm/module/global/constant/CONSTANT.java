package com.questionnaire.ssm.module.global.constant;

/**
 * Created by 郑晓辉 on 2017/4/16.
 * Description: 系统常量
 */
public class CONSTANT {
    /*上传文件路径*/
    private final static String UPLOAD_FILE_PATH = "E:\\创新实践\\uploadFiles\\excels";
    /*服务器文件路径*/
    private final static String DOWNLOAD_FOLDER_PATH = "E:\\创新实践\\uploadFiles\\uploadFile-template";

    public static String getUploadFilePath() {
        return UPLOAD_FILE_PATH;
    }

    public static String getDownloadFolderPath() {
        return DOWNLOAD_FOLDER_PATH;
    }
}
