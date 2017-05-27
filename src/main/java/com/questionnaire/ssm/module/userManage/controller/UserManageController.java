package com.questionnaire.ssm.module.userManage.controller;

import com.questionnaire.ssm.module.generated.pojo.Role;
import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.userManage.enums.UserActionEnum;
import com.questionnaire.ssm.module.userManage.pojo.AllRoleInfoVO;
import com.questionnaire.ssm.module.userManage.pojo.NewUserAuthorityInfo;
import com.questionnaire.ssm.module.userManage.pojo.RoleAuthorityVO;
import com.questionnaire.ssm.module.userManage.pojo.UploadResultVO;
import com.questionnaire.ssm.module.userManage.service.UploadFileService;
import com.questionnaire.ssm.module.userManage.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/16.
 * Description: 用户管理，包括用户信息数据上传、单位信息上传
 */
@Controller
@RequestMapping("/userManage")
public class UserManageController {

    /**
     * 获取上传数据视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getUploadView")
    public ModelAndView getUploadView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload/uploadExcel");
        return modelAndView;
    }

    /**
     * 上传数据
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/uploadData")
    @ResponseBody
    public ResponsePkt uploadData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<UploadResultVO> resultVOList = uploadFileService.uploadFile(request, response);
        this.uploadResultVOList = resultVOList;
        return ResultUtil.success(resultVOList);
    }

    /**
     * 获取上传结果视图
     *
     * @return
     * @throws Exception
     */
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
    @GetMapping(value = "/downloadUploadTemplate")
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

    /**
     * 获取人员权限信息视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getRoleAuthorityView")
    public String getRoleAuthorityView() throws Exception {
        return "roleAuthority/roleAuthority";
    }

    /**
     * 获取人员权限管理视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getRoleAuthorityManageView")
    public ModelAndView getRoleAuthorityManageView(@RequestParam("userAccount") String userAccount,
                                                   @RequestParam("accountOwner") String accountOwner,
                                                   @RequestParam("userRole") String userRole) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userAccount", userAccount);
        modelAndView.addObject("accountOwner", accountOwner);
        modelAndView.addObject("userRole", userRole);
        modelAndView.setViewName("roleAuthority/roleAuthority_manage");
        System.out.println(userAccount + "||" + accountOwner + "||" + userRole);
        return modelAndView;
    }

    /**
     * 获取当前用户可以管理的人员权限信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getRoleAuthority")
    @ResponseBody
    public List<RoleAuthorityVO> getRoleAuthority() throws Exception {
        String userTel = UserValidationUtil.getUserTel(logger);
        return userInfoService.listUserRoleAuthorityInfo(userTel);
    }

    /**
     * 批量删除用户账户
     *
     * @param userAccountArray
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/multiDeleteUserAccount")
    @ResponseBody
    public ResponsePkt multiDeleteUserAccount(@RequestParam("userAccountArray") String[] userAccountArray) throws Exception {
        if (userAccountArray.length <= 0) {
            return ResultUtil.success();
        }
        //并不是每一个账户都可以成功删除
        return ResultUtil.success(userInfoService.operateUserAccount(Arrays.asList(userAccountArray),
                UserActionEnum.DELETE_USER_ACCOUNT));
    }

    /**
     * 批量启用用户账户
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/multiEnableUserAccount")
    @ResponseBody
    public ResponsePkt multiEnableUserAccount(@RequestParam("userAccountArray") String[] userAccountArray) throws Exception {
        if (userAccountArray.length <= 0) {
            return ResultUtil.success();
        }
        userInfoService.operateUserAccount(Arrays.asList(userAccountArray), UserActionEnum.ENABLE_USER_ACCOUNT);
        return ResultUtil.success();
    }

    /**
     * 批量禁用用户账户
     *
     * @param userAccountArray
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/multiDisableUserAccount")
    @ResponseBody
    public ResponsePkt multiDisableUserAccount(@RequestParam("userAccountArray") String[] userAccountArray) throws Exception {
        if (userAccountArray.length <= 0) {
            return ResultUtil.success();
        }
        userInfoService.operateUserAccount(Arrays.asList(userAccountArray), UserActionEnum.DISABLE_USER_ACCOUNT);
        return ResultUtil.success();
    }

    /**
     * 获取所有角色信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getAllRole")
    @ResponseBody
    public List<AllRoleInfoVO> getAllRole() throws Exception {
        return userInfoService.listAllRole();
    }

    /**
     * 更新用户权限相关信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/updateUserAuthorityInfo")
    @ResponseBody
    public ResponsePkt updateUserAuthorityInfo(@RequestBody NewUserAuthorityInfo newUserAuthorityInfo) throws Exception {
        //用户参数校验
        if (newUserAuthorityInfo.getUserAccount().isEmpty()
                || newUserAuthorityInfo.getUserAccount() == null) {
            return ResultUtil.error(CodeForVOEnum.NEW_USER_AUTHORITY_VO_DATA_ERROR.getCode(),
                    CodeForVOEnum.NEW_USER_AUTHORITY_VO_DATA_ERROR.getMessage());
        }
        userInfoService.updateUserAuthorityInfo(newUserAuthorityInfo);
        return ResultUtil.success();
    }


    private List<UploadResultVO> uploadResultVOList;
    private final static Logger logger = LoggerFactory.getLogger(UserManageController.class);
    private UploadFileService uploadFileService;
    private UserInfoService userInfoService;

    @Autowired
    public UserManageController(UploadFileService uploadFileService,
                                UserInfoService userInfoService) {
        this.uploadFileService = uploadFileService;
        this.userInfoService = userInfoService;
    }
}
