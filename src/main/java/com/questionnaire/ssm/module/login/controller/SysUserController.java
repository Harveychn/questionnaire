package com.questionnaire.ssm.module.login.controller;

import com.questionnaire.ssm.module.generated.pojo.User;
import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.login.pojo.LoginVO;
import com.questionnaire.ssm.module.login.pojo.NewPasswordVO;
import com.questionnaire.ssm.module.login.service.LoginService;
import com.questionnaire.ssm.module.login.service.UserService;
import com.questionnaire.ssm.module.login.utils.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.Set;

/**
 * 用户登录管理，登陆成功后
 * 可以通过 subject.getSession 中的 userTel 获取当前登录用户名
 */
@Controller
@RequestMapping("/user")
public class SysUserController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    /**
     * 获取登录登录视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getLoginView")
    public ModelAndView getLoginView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loginVO", new LoginVO());
        modelAndView.addObject("errorMessage", null);
        modelAndView.setViewName("login/login");
        return modelAndView;
    }

    /**
     * 登录
     *
     * @param loginVO
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/login")
    public ModelAndView login(@Valid LoginVO loginVO, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("login/login");
            return modelAndView;
        }

        Subject subject = SecurityUtils.getSubject();
        String errorMessage = UserUtil.subjectLogin(subject, loginVO, request);

        if (null != errorMessage) {
            modelAndView.addObject("errorMessage", errorMessage);
            modelAndView.addObject("user", loginVO);
            modelAndView.setViewName("login/login");
            return modelAndView;
        }
        //用户登录系统记录信息保存
        User userLoginRecord = new User();
        userLoginRecord.setUserTel(loginVO.getUserTel());
        userLoginRecord.setLastLoginIp(UserUtil.getClientIp(request));
        userLoginRecord.setLastLoginDate(new Date());
        userService.updateUserLoginRecord(userLoginRecord);

        subject.getSession().setAttribute("userTel", loginVO.getUserTel());
        modelAndView.addObject("user", loginVO);

        Set<String> userRoleSet = loginService.listUserRole(loginVO.getUserTel());
        if (userRoleSet.contains(CONSTANT.getRoleSystemAdmin())) {
            modelAndView.setViewName("SystemAdminIndex");
        }
        if (userRoleSet.contains(CONSTANT.getRoleCenterAdmin())) {
            modelAndView.setViewName("CenterAdminIndex");
        }
        if (userRoleSet.contains(CONSTANT.getRoleResearcher())) {
            modelAndView.setViewName("login/nonPrivilegedTip");
        }

        return modelAndView;
    }

    @GetMapping(value = "/logout")
    public String logout() throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            subject.logout();
        }
        return "redirect:/user/getLoginView";
    }

    /**
     * 获取的新密码视图
     *
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/newPasswordView")
    public String newPasswordView(Model model) throws Exception {
        model.addAttribute("newPasswordVO", new NewPasswordVO());
        return "login/changePassword";
    }

    /**
     * 修改密码
     *
     * @param newPasswordVO
     * @param result
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/changPassword")
    public String changPassword(@Valid NewPasswordVO newPasswordVO, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "login/changePassword";
        }
        String userTel = UserValidationUtil.getUserTel(logger);
        userService.updateUserPassword(userTel, newPasswordVO);
        return "login/newKeySuccess";
    }

    private UserService userService;
    private LoginService loginService;

    @Autowired
    public SysUserController(UserService userService,
                             LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }
}
