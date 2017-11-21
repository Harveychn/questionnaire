package com.questionnaire.ssm.module.global.controller;

import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.CheckPicUtil;
import com.questionnaire.ssm.module.global.util.DownloadPicUtil;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * creator: xiaohui zheng
 * time: 2017/10/29  9:19
 * function: 图片上传控制器
 */
@RestController
@RequestMapping(value = "fileIo")
public class UploadPictureController {

    @PostMapping(value = "/upload/picture/qesPaper")
    public ResponsePkt postPicAndReturnPath(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multiFileRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multiFileRequest.getFiles("file");

        String fileName = null, savePath = null, picRelativePath = null, todayDateStr = null;
        for (MultipartFile multipartFile : files) {
            fileName = multipartFile.getOriginalFilename();
            if (!CheckPicUtil.isPictureType(fileName, multipartFile.getContentType())) {
                return ResultUtil.badRequest();
            }
            //新建一个文件夹
            todayDateStr = CheckPicUtil.getTodayFolder();
            File picFolder = new File(CONSTANT.UPLOAD_PICTURE_QUESTION + "\\" + todayDateStr);
            if (!picFolder.exists()) {
                picFolder.mkdirs();
            }
            picRelativePath = todayDateStr + "\\" + UUID.randomUUID().toString() + CheckPicUtil.getPicType(fileName);
            File savingPic = new File(CONSTANT.UPLOAD_PICTURE_QUESTION + "\\" + picRelativePath);
            multipartFile.transferTo(savingPic);
        }

        return ResultUtil.success(picRelativePath);
    }

    /**
     * 下载图片文件
     *
     * @param picRelativePath 图片文件相对路径
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/download/picture/qesPaper")
    public String downloadPic(String picRelativePath) throws Exception {
        picRelativePath = picRelativePath.replace("/", "\\");
        return DownloadPicUtil.getPicBase64Str(CONSTANT.UPLOAD_PICTURE_QUESTION, picRelativePath);
    }

    /**
     * 用于前台引用图片文件
     *
     * @param picRelativePath 图片在服务器的相对路径
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/refPic")
    public ResponseEntity<byte[]> refPicDest(HttpServletRequest request, String picRelativePath) throws Exception {
        picRelativePath = picRelativePath.replace("/", "\\");
        return DownloadPicUtil.download(CONSTANT.UPLOAD_PICTURE_QUESTION, picRelativePath);
    }
}
