package com.example.mybatisdemo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Verify {
    /**
     * 邮箱
     */
    private String email;

    private String code;
}