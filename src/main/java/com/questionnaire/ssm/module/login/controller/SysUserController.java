package com.questionnaire.ssm.module.login.controller;

import com.questionnaire.ssm.module.login.pojo.LoginVO;
import com.questionnaire.ssm.module.login.pojo.NewPasswordVO;
import com.questionnaire.ssm.module.login.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
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

/**
 * 用户登录管理，登陆成功后
 * 可以通过 subject.getSession 中的 userTel 获取当前登录用户名
 */
@Controller
@RequestMapping("/user")
public class SysUserController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @GetMapping(value = "/getLoginView")
    public ModelAndView getLoginView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loginVO", new LoginVO());
        modelAndView.addObject("errorMessage", null);
        modelAndView.setViewName("login/login");
        return modelAndView;
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@Valid LoginVO loginVO, BindingResult bindingResult, HttpServletRequest request, Model model) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(loginVO.toString());
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("login/login");
            return modelAndView;
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginVO.getUserTel(), loginVO.getPassword());
        if (null == loginVO.getRememberMe()) {
            token.setRememberMe(false);
            subject.isRemembered();
        } else {
            token.setRememberMe(true);
        }

        String errorMessage = null;
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            request.getSession().setAttribute("user", loginVO);
            errorMessage = "用户名/密码错误！";
        } catch (ExcessiveAttemptsException e) {
            request.getSession().setAttribute("user", loginVO);
            errorMessage = "错误次数超过限制，请您于10分钟后再试！";
        } catch (UnauthorizedException e) {
            request.getSession().setAttribute("user", loginVO);
            errorMessage = "您没有得到相应的授权！";
        } catch (UnknownAccountException e) {
            errorMessage = "账号信息不存在！";
        } catch (LockedAccountException e) {
            errorMessage = "您的账户信息被锁定，请联系系统管理员！";
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        if (null != errorMessage) {
            modelAndView.addObject("errorMessage", errorMessage);
            modelAndView.addObject("user", loginVO);
            modelAndView.setViewName("login/login");
            return modelAndView;
        }
        subject.getSession().setAttribute("userTel", loginVO.getUserTel());
        modelAndView.addObject("user", loginVO);
        modelAndView.setViewName("index");
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

    @GetMapping(value = "/newPasswordView")
    public String newPasswordView(Model model) throws Exception {
        model.addAttribute("newPasswordVO", new NewPasswordVO());
        return "login/changePassword";
    }

    @PostMapping(value = "/changPassword")
    public String changPassword(@Valid NewPasswordVO newPasswordVO, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "login/changePassword";
        }
        userService.updateUserPassword(newPasswordVO);
        return "login/newKeySuccess";
    }

    private UserService userService;

    @Autowired
    public SysUserController(UserService userService) {
        this.userService = userService;
    }
}
