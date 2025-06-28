package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.entity.LoginChangePassward;
import com.example.mybatisdemo.entity.Result;
import com.example.mybatisdemo.entity.User;
import com.example.mybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 * 提供RESTful API接口
 */
@RestController
@RequestMapping("/user")
//@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 修改密码
     */
    @PostMapping("/updatePassword")
    public Result changePassword(@RequestBody LoginChangePassward loi) {
        return userService.changePassword(loi);
    }

} 