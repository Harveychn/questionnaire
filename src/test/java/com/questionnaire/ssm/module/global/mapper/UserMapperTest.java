package com.questionnaire.ssm.module.global.mapper;

import com.questionnaire.ssm.module.global.pojo.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;


/**
 * Created by 郑晓辉 on 2017/3/20.
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void updateByPrimaryKeySelective() throws Exception {
        User user = new User();
        user.setUserTel("11111111111");
        user.setUserRealName("admin");
        user.setId("111");
        user.setRoleId(1L);
        user.setUnitId(1L);
        user.setMailAddress("980278090@qq.com");
        user.setUserBirthday(new Date());
        user.setIsValid(true);
        user.setUserSex(1);

        //加密处理
        String algorithmName = "md5";
        String password = "2222";
        int hashIterations = 3;
        SimpleHash hash = new SimpleHash(algorithmName, password,
                user.getUserRealName() + user.getMailAddress(), hashIterations);
        String encodedPassword = hash.toHex();
        System.out.println(encodedPassword);

        user.setPassword(encodedPassword);

        System.out.println(userMapper.updateByPrimaryKeySelective(user) == 1);
    }

}