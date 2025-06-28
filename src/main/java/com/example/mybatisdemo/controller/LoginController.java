package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.entity.*;
import com.example.mybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
            LoginInfo info = userService.login(user);

            if (info != null) {
                System.out.println(info.getEmail());
                return Result.success(info);
            }
            return Result.error("邮箱或密码输入错误");
    }

    /**
     * 注册账号
     */
    @PostMapping("/register")
    public Result register(@RequestBody Register register) {
        System.out.println(register.getEmail());
        return userService.register(register);
    }

    /**
     * 发送验证码
     */
    @GetMapping("/register/sendEmail")
    public Result sendEmail(@RequestParam String email) {
        userService.sendEmail(email,"prove");
        return Result.success();
    }

    @PostMapping("/register/verify-code")
    public Result verify_code(@RequestBody Verify verify) {
        System.out.println(verify.getEmail());
        return Result.success();
        //return userService.verify_code(verify);
    }
}
