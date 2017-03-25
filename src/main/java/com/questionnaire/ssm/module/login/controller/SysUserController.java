package com.questionnaire.ssm.module.login.controller;

import com.questionnaire.ssm.module.login.pojo.LoginVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class SysUserController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @PostMapping(value = "/login")
    public String login(LoginVO loginVO, HttpServletRequest request, Model model) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginVO.getUserTel(), loginVO.getPassword());
        if (null == loginVO.getRememberMe()) {
            token.setRememberMe(false);
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
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("user", loginVO);
            return "login/retryLogin";
        } else {
            subject.getSession().setAttribute("userTel", loginVO.getUserTel());
            model.addAttribute("user", loginVO);
            return "login/success";
        }
    }

    @GetMapping(value = "/logout")
    public String logout() throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            subject.logout();
        }
        return "redirect:/";
    }


    @GetMapping(value = "/testRoleLimit")
    @ResponseBody
    public String testRoleLimit() throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("系统管理员")) {
            return "role OK";
        } else {
            return "role Fail";
        }
    }

    @GetMapping(value = "/testPermissionLimit")
    @ResponseBody
    public String testPermissionLimit() throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isPermitted("权限1")) {
            return "permission OK";
        } else {
            return "permission Fail";
        }
    }

}
