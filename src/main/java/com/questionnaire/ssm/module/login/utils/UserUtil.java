package com.questionnaire.ssm.module.login.utils;

import com.questionnaire.ssm.module.generated.pojo.User;
import com.questionnaire.ssm.module.login.pojo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 郑晓辉 on 2017/3/20.
 * Description: login模块用户工具类
 */
public class UserUtil {

    public static String subjectLogin(Subject subject, LoginVO loginVO, HttpServletRequest request) throws Exception {
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
        return errorMessage;
    }

    /**
     * 如果更新密码，则加密密码
     *
     * @param user
     * @return 密码加密后的user
     * @throws Exception
     */
    public static User encoded(User user) throws Exception {
        if (user.getPassword() != null) {
            user.setPassword(encodePassword(user));
        }
        return user;
    }

    /**
     * 获取设置的新密码
     *
     * @param user
     * @return
     */
    public static String encodePassword(User user) {
        String algorithmName = "md5";
        int hashIterations = 3;
        SimpleHash hash = new SimpleHash(algorithmName, user.getPassword(),
                user.getUserTel() + user.getUserRealName(), hashIterations);
        return hash.toHex();
    }

    /**
     * 获取设置的新密码
     *
     * @param password     未加密密码
     * @param userTel      用户账户（电话号码）
     * @param userRealName 用户真实姓名
     * @return 加密后的密码
     */
    public static String encodePassword(String password, String userTel, String userRealName) {
        String algorithmName = "md5";
        int hashIterations = 3;
        SimpleHash hash = new SimpleHash(algorithmName, password,
                userTel + userRealName, hashIterations);
        return hash.toHex();
    }

    public static String getClientIp(HttpServletRequest request)throws Exception{
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    private final static Logger logger = LoggerFactory.getLogger(UserUtil.class);
}
