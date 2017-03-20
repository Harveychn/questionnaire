package com.questionnaire.ssm.module.login.controller;

import com.questionnaire.ssm.module.global.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class SysUserController {

    private static final Logger LOG = LoggerFactory.getLogger(SysUserController.class);

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request, Model model) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserTel(), user.getPassword());
        String errorMessage = null;
        try {
            subject.login(token);
            token.setRememberMe(true);
        } catch (IncorrectCredentialsException e) {
            request.getSession().setAttribute("user", user);
            errorMessage = "用户名/密码错误！";
        } catch (ExcessiveAttemptsException e) {
            request.getSession().setAttribute("user", user);
            errorMessage = "错误次数超过限制，请您于10分钟后再试！";
        } catch (UnauthorizedException e) {
            request.getSession().setAttribute("user", user);
            errorMessage = "您没有得到相应的授权！";
        } catch (UnknownAccountException e) {
            errorMessage = "账号信息不存在！";
        } catch (LockedAccountException e) {
            errorMessage = "您的账户信息被锁定，请联系系统管理员！";
        }
        if (null != errorMessage) {
            request.getSession().setAttribute("error", errorMessage);
            return "redirect:/";
        } else {
            model.addAttribute("user", user);
            return "login/success";
        }
    }

    @RequestMapping("/logout")
    public String logout() throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            subject.logout();
        }
        return "redirect:/";
    }

}
