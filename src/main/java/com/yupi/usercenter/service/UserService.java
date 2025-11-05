package com.yupi.usercenter.service.impl;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.usercenter.model.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
* @author zengzhen
* @description 针对表【user】的数据库操作Service
* @createDate 2025-10-29 15:01:13
*/
public interface UserService extends IService<User> {


    /**
     * 用户注释
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     *
     * 用户登录
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     *  用户脱敏
     * @param originUser
     * @return 脱敏后的用户数据
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     * @param
     * @return
     */
    int userLogout(HttpServletRequest request);
}










