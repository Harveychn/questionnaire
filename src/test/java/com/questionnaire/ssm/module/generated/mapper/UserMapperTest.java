package com.questionnaire.ssm.module.generated.mapper;

import com.questionnaire.ssm.module.generated.pojo.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Date;


/**
 * Created by 郑晓辉 on 2017/3/20.
 * Description:
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void updateByPrimaryKeySelective() throws Exception {
        User user = new User();
//        user.setUserTel("17722223333");
        user.setUserTel("17764591959");
        user.setUserRealName("admin");
//        user.setId("111");
        user.setRoleId(2L);
        user.setRoleId(1L);
        user.setUnitId(1L);
        user.setMailAddress("980278090@qq.com");
        user.setUserBirthday(new Date());
        user.setIsValid(true);
        user.setUserSex(0);

        //加密处理
        String algorithmName = "md5";
        String password = "222222";
        int hashIterations = 3;
        SimpleHash hash = new SimpleHash(algorithmName, password,
                user.getUserTel() + user.getUserRealName(), hashIterations);
        String encodedPassword = hash.toHex();
//        System.out.println(encodedPassword);

        user.setPassword(encodedPassword);

        int resultCode = 0;
        try {
            resultCode = userMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            System.out.println("【MSG】" + e.getMessage() + "\n【CAUSE】" + e.getCause());
        }
        Assert.assertEquals(resultCode, 1);
//        System.out.println(resultCode == 1);
    }

}