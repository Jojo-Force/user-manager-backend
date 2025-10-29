package com.yupi.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yupi.usercenter.mapper.UserMapper;
import com.yupi.usercenter.model.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author zengzhen
* @description 用户服务实现类
* @createDate 2025-10-29 15:01:13
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements com.yupi.usercenter.service.impl.UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword){
        //1.校验
        //非空
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
           return -1;
        }

        //账号密码不小于4位
        if (userAccount.length() < 4) {
            return -1;
        }

        //密码就不小于8位吧
        if(userPassword.length() < 8 || checkPassword.length() < 8){
            return -1;
        }

        // 账号不能包含特殊字符
        String validPattern = "[_`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\\\\n|\\\\r|\\\\t]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if(matcher.find()){
            return -1;
        }

        //密码和校验密码相同
        if(!userPassword.equals(checkPassword)){
            return -1;
        }

        //账号不能重复，放到最后查询数据库
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account",userAccount);
        long count = this.count(queryWrapper);
        if (count > 0){
            return -1;
        }

        //2.加密
        final String SALT = "yupi";
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        User user = new User();

        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if (!saveResult){
            return -1;
        }

        return user.getId();
    }

}




























