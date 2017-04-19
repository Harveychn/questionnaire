package com.questionnaire.ssm.module.shiro.realm;

import com.questionnaire.ssm.module.generated.pojo.User;
import com.questionnaire.ssm.module.login.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 郑晓辉 on 2017/3/12.
 */
public class MyRealm extends AuthorizingRealm {

    // 验证当前登录的用户，获取认证信息(认证)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        String userTel = (String) authenticationToken.getPrincipal();
        User user = null;
        try {
            user = loginService.getUser(userTel);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        if (null == user) {
            throw new UnknownAccountException();
        }
        if (!user.getIsValid()) { //账户不可用
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(user.getUserTel(), user.getPassword(),
                ByteSource.Util.bytes(user.getUserTel() + user.getUserRealName()), getName());
    }

    //为当前登陆成功的用户授予权限和角色(授权)
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userTel = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //设置用户角色
        try {
            simpleAuthorizationInfo.setRoles(loginService.listUserRole(userTel));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        //设置用户权限
        try {
            simpleAuthorizationInfo.setStringPermissions(loginService.listUserPermission(userTel));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return simpleAuthorizationInfo;
    }

    private LoginService loginService;

    @Autowired
    public MyRealm(LoginService loginService) {
        this.loginService = loginService;
    }

    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);


}
