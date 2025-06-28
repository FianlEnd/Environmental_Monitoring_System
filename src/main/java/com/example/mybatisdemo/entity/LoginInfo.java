package com.example.mybatisdemo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * @Description: 登录后传给前端的信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {
    private Long userId;
    private String email;
    private String role;
    private String token;
}
