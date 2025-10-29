package com.yupi.usercenter.service.impl;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.usercenter.model.domain.User;

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
}
