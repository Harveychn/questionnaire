package com.questionnaire.ssm.module.global.util;

import com.questionnaire.ssm.module.global.constant.CONSTANT;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * creator: xiaohui zheng
 * time: 2017/10/29  18:39
 * function: 图片下载工具类
 */
public class DownloadPicUtil {

    /**
     * 根据路径下载图片文件
     *
     * @param picRelativePath
     * @return
     * @throws IOException
     */
    public static ResponseEntity<byte[]> download(String servAbsolutePath, String picRelativePath) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        String filePath = null;
        if (picRelativePath == null) {
            picRelativePath = CONSTANT.getUserDefaultPicture();
        }
        filePath = servAbsolutePath + "\\" + picRelativePath.trim();
        File file = new File(filePath);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String charset = new String(picRelativePath.getBytes("utf-8"), "iso-8859-1");
        headers.setContentDispositionFormData("file", charset);
        byte[] fileByteArray = new byte[0];
        try {
            fileByteArray = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            fileByteArray = FileUtils.readFileToByteArray(
                    new File(servAbsolutePath + "\\" + CONSTANT.getUserDefaultPicture()));
        }
        return new ResponseEntity<>(fileByteArray, headers, HttpStatus.CREATED);
    }

    public static String outputPicStream(HttpServletResponse response, String servAbsolutePath, String picRelativePath) throws IOException {
        String filePath = null;
        if (picRelativePath == null) {
            picRelativePath = CONSTANT.getUserDefaultPicture();
        }
        filePath = servAbsolutePath + "\\" + picRelativePath.trim();
        File file = new File(filePath);

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (inputStream == null) {
            return null;
        }
        byte[] data = new byte[inputStream.available()];
        inputStream.read(data);
        inputStream.close();
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}
