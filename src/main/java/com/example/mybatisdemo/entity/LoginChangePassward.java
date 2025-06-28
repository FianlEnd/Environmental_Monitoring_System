package com.example.mybatisdemo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * @Description: 登录或修改密码实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginChangePassward {
    private String oldPassword;
    private String newPassword;
    private String username;
    private String password;
    private String confirmPassword;
}
