package com.questionnaire.ssm.module.global.constant;

/**
 * Created by 郑晓辉 on 2017/4/16.
 * Description: 系统常量
 */
public class CONSTANT {
    /*上传文件路径*/
    private final static String UPLOAD_FILE_PATH = "E:\\创新实践\\uploadFiles\\excels";
    /*服务器模板文件路径*/
    private final static String DOWNLOAD_FOLDER_PATH = "E:\\创新实践\\uploadFiles\\uploadFile-template";
    //数据库中单位id文本切割符
    private final static String DIVIDE_SYMBOL = "||";
    /*服务器用户头像路径*/
    private final static String USER_PICTURE_PATH = "E:\\创新实践\\uploadFiles\\user-pictures";
    /*默认头像*/
    private final static String USER_DEFAULT_PICTURE = "default.jpg";

    public static String getUploadFilePath() {
        return UPLOAD_FILE_PATH;
    }

    public static String getDownloadFolderPath() {
        return DOWNLOAD_FOLDER_PATH;
    }

    public static String getDivideSymbol() {
        return DIVIDE_SYMBOL;
    }

    public static String getUserPicturePath() {
        return USER_PICTURE_PATH;
    }

    public static String getUserDefaultPicture() {
        return USER_DEFAULT_PICTURE;
    }
}
