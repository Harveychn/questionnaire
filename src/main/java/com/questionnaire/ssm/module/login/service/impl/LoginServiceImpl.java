package com.questionnaire.ssm.module.login.service.impl;

import com.questionnaire.ssm.module.generated.mapper.UserMapper;
import com.questionnaire.ssm.module.generated.pojo.User;
import com.questionnaire.ssm.module.login.mapper.SysUserMapper;
import com.questionnaire.ssm.module.login.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by 郑晓辉 on 2017/3/13.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public User getUser(String userTel) throws Exception {
        return userMapper.selectByPrimaryKey(userTel);
    }

    @Override
    public Set<String> listUserRole(String userTel) throws Exception {
        return sysUserMapper.listUserRole(userTel);
    }

    @Override
    public Set<String> listUserPermission(String userTel) throws Exception {
        return sysUserMapper.listUserPermission(userTel);
    }
}
