package com.questionnaire.ssm.module.login.controller;

import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.login.service.LoginService;
import com.questionnaire.ssm.module.login.service.UserService;
import com.questionnaire.ssm.module.login.utils.VCodeUtils;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by 郑晓辉 on 2017/6/19.
 * Description: 找回密码
 */
@Controller
@RequestMapping(value = "/retrievePwd")
public class RetrievePwdController {

    /**
     * 获取找回密码--确认账户视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getRetrievePwdTelCheckView")
    public ModelAndView getRetrievePwdView() throws Exception {
        return new ModelAndView("login/retrievePwd-telCheck");
    }

    /**
     * 获取重置密码视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getResetPwdView")
    public ModelAndView getResetPwdView() throws Exception {
        return new ModelAndView("/login/retrievePwd-newPwd");
    }

    /**
     * 获取验证码
     *
     * @param phone   要找回的账户手机号码
     * @param session
     * @return
     * @throws ApiException
     */
    @PostMapping(value = "/getVerifyCode")
    @ResponseBody
    public ResponsePkt getVCode(String phone, HttpSession session) throws ApiException {
        try {
            if (null == loginService.getUser(phone)) {
                return ResultUtil.error(CodeForVOEnum.NO_SUCH_USER_INFO.getCode(),
                        CodeForVOEnum.NO_SUCH_USER_INFO.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultUtil.error(CodeForVOEnum.UNKNOWN_ERROR.getCode(),
                    CodeForVOEnum.UNKNOWN_ERROR.getMessage());
        }

        TaobaoClient client = new DefaultTaobaoClient("https://eco.taobao.com/router/rest",
                "24443810",
                "1b198aec0a2e5e38fbdb0478bcc55121");
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("");
        req.setSmsType("normal");
        req.setSmsFreeSignName("全国流行病问卷调查系统");
        //生成验证码数字
        String verifyCode = VCodeUtils.gentVcode(6);
        //存到session域中
        session.setAttribute("verifyCode", verifyCode);

        logger.info("验证码：" + verifyCode);

        req.setSmsParamString("{number:'" + verifyCode + "'}");
        req.setRecNum(phone);
        req.setSmsTemplateCode("SMS_71220922");
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);

        return ResultUtil.success(rsp.getBody());
    }

    /**
     * 校验验证码
     *
     * @param phone
     * @param verifyCode
     * @param httpSession
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/checkVCode")
    @ResponseBody
    public ResponsePkt checkVCode(String phone, String verifyCode, HttpSession httpSession) throws Exception {
        //校验正确
        if (String.valueOf(httpSession.getAttribute("verifyCode")).equals(verifyCode)) {
            this.userTel = phone;
            return ResultUtil.success("/retrievePwd/getResetPwdView");
        }
        //校验失败
        return ResultUtil.error(CodeForVOEnum.VERIFY_CODE_ERROR.getCode(),
                CodeForVOEnum.VERIFY_CODE_ERROR.getMessage());
    }

    /**
     * 重置密码
     *
     * @param newPwdStr
     * @param httpSession
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/resetPwd")
    @ResponseBody
    public ResponsePkt resetPwd(String newPwdStr, HttpSession httpSession) throws Exception {
        if (newPwdStr.length() < 6) {
            return ResultUtil.error(CodeForVOEnum.PASSWORD_TOO_SHORT.getCode(),
                    CodeForVOEnum.PASSWORD_TOO_SHORT.getMessage());
        }
        if (this.userTel == null) {
            return ResultUtil.error(CodeForVOEnum.REQUEST_ERROR.getCode(),
                    CodeForVOEnum.REQUEST_ERROR.getMessage());
        }
        if (String.valueOf(httpSession.getAttribute("verifyCode")).isEmpty()) {
            return ResultUtil.error(CodeForVOEnum.VERIFY_CODE_TIMEOUT.getCode(),
                    CodeForVOEnum.VERIFY_CODE_TIMEOUT.getMessage());
        }
        userService.resetUserPwd(this.userTel, newPwdStr);
        return ResultUtil.success();
    }


    //正在操作的用户
    private String userTel;
    private static final Logger logger = LoggerFactory.getLogger(RetrievePwdController.class);
    private LoginService loginService;
    private UserService userService;

    @Autowired
    public RetrievePwdController(LoginService loginService,
                                 UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }
}
