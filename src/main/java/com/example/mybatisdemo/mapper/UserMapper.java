package com.example.mybatisdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisdemo.entity.User;
import com.example.mybatisdemo.entity.Validation;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 用户Mapper接口
 * 使用注解和SQL明文方式实现CRUD操作
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据邮箱和密码查询用户
     */
    @Select("SELECT * FROM users WHERE email = #{email} AND password = #{password}")
    User selectByEmailandPassword(@Param("email") String email,@Param("password")String password);

//    /*
//     * 根据名字和密码查询用户
//     */
//   @Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
//   User selectByUsernameandPassword(@Param("username") String username,@Param("password")String password);

    /**
     * 根据邮箱查询用户
     */
    @Select("SELECT * FROM users WHERE email = #{email}")
    User selectByEmail(@Param("email") String email);

    /**
     * 根据邮箱查询用户
     */
    @Select("SELECT * FROM validation WHERE email = #{email}")
    Validation selectByEmailVali(@Param("email") String email);

    @Update("UPDATE users SET password = #{password}, updated_at = NOW() WHERE user_id = #{userId}")
    int updatePasswordById(@Param("password") String password,@Param("userId") Long userId);

    @Delete("DELETE FROM validation WHERE id = #{id}")
    int deleteByIdVali(Long id);

    @Insert("INSERT INTO validation (email, code, time) VALUES (#{email}, #{code}, #{time})")
    int insertVali(Validation validation);
}