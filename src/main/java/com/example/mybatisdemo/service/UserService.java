package com.example.mybatisdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisdemo.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 用户服务接口
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     */
    LoginInfo login(User user);

    Result register(Register register);

    Result changePassword(LoginChangePassward logi);

    Result sendEmail(String email, String content);
}