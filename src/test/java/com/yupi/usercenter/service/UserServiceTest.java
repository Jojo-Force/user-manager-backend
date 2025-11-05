package com.yupi.usercenter.service;

import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.service.impl.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 用户服务测试
 *
 * @author yupi
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("test");
        user.setAvatarUrl("https://i-operation.csdnimg.cn/ad/ad_pic/f64c9ceb29c24f2db750e067d1a0d69b.jpeg");
        user.setUserAccount("123456");
        user.setGender(0);
        user.setUserPassword("123456");
        user.setPhone("123");
        user.setEmail("123@qq.com");

        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assert.assertTrue(result);
    }

    @Test
    public void userRegister() {
        String userAccount = "test";
        String userPassword = "";
        String checkPassword = "12345678";
        String planetCode = "123";
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assert.assertEquals(-1,result);

        userAccount = "tes";
        userPassword = "12345678";
        checkPassword = "12345678";
        planetCode = "123";
        result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assert.assertEquals(-1,result);

        userAccount = "test";
        userPassword = "123456";
        checkPassword = "123456";
        planetCode = "123";
        result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assert.assertEquals(-1,result);


        userAccount = "te@# $st";
        userPassword = "12345678";
        checkPassword = "12345678";
        planetCode = "123";
        result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assert.assertEquals(-1,result);

        userAccount = "test";
        userPassword = "12345678";
        checkPassword = "123456789";
        planetCode = "123";
        result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assert.assertEquals(-1,result);

        userAccount = "testoo";
        userPassword = "12345678";
        checkPassword = "12345678";
        planetCode = "123";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assert.assertTrue(result > 0);
    }
}























