package com.example.mybatisdemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 注册实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class  Register{

    /**
     * 主键ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色：ENV_OFFICER, CITY_MANAGER, PUBLIC
     */
    private String role;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


    private String code;

    /**
     * 角色枚举
     */
    public enum Role {
        ENV_OFFICER,
        CITY_MANAGER,
        PUBLIC
    }
}