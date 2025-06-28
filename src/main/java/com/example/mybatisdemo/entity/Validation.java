package com.example.mybatisdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/*
 * @Description: 登录后传给前端的信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Validation {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long id;
    @TableField("email")
    private String email;
    @TableField("code")
    private String code;
    @TableField("time")//过期时间
    private LocalDateTime time;
}