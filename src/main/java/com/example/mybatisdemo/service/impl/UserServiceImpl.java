package com.example.mybatisdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisdemo.entity.*;
import com.example.mybatisdemo.mapper.UserMapper;
import com.example.mybatisdemo.service.UserService;
import com.example.mybatisdemo.utils.CurrentHolder;
import com.example.mybatisdemo.utils.EmailApi;
import com.example.mybatisdemo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import cn.hutool.core.util.RandomUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 用户服务实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public LoginInfo login(User user) {
        System.out.println(user.getPassword());
        User e = baseMapper.selectByEmailandPassword(user.getEmail(),user.getPassword());
        if(e!=null){
            //生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getUserId());
            claims.put("username", e.getEmail());
            String jwt = JwtUtils.generateToken(claims);
            return new LoginInfo(e.getUserId(),e.getEmail(),e.getRole(),jwt);
        }
        //不存在这个用户返回null
        return null;
    }


    // 用户名校验正则表达式
    private static final String USERNAME_REGEX = "^[a-z0-9_-]{3,50}$";
    // 密码校验正则表达式（包含大小写字母、数字、特殊字符）
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,128}$";

    @Override
    public Result register(Register register) {
        User user = new User();
        user.setEmail(register.getEmail());
        user.setUsername(register.getUsername());
        user.setPassword(register.getPassword());
        user.setRole(register.getRole());

        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();

        System.out.println(register.getEmail());

        Validation validation=baseMapper.selectByEmailVali(email);

        System.out.println(validation.getCode());
        System.out.println(register.getCode());

        if(validation!=null){
            if(validation.getTime().isBefore(LocalDateTime.now())){
                baseMapper.deleteByIdVali(validation.getId());
                return Result.error("验证码已过期");
            }
            if(!validation.getCode().equals(register.getCode())){
                return Result.error("验证码错误");
            }
        }else{
            return Result.error("验证码错误");
        }
        // 校验用户名是否为空
        if (StringUtils.isEmpty(username)) {
            return Result.error("用户名不能为空");
        }

//        // 校验用户名格式
//        if (!Pattern.matches(USERNAME_REGEX, username)) {
//            return Result.error("用户名必须是3-50位小写字母、数字、下划线或连字符");
//        }

        // 检查邮箱是否已存在
        if (baseMapper.selectByEmail(email) != null) {
            return Result.error("邮箱已存在");
        }

        // 校验密码是否为空
        if (StringUtils.isEmpty(password)) {
            return Result.error("密码不能为空");
        }

//        // 校验密码格式
//        if (!Pattern.matches(PASSWORD_REGEX, password)) {
//            return Result.error("密码必须是8-128位，包含大小写字母、数字和特殊字符");
//        }

        // 设置默认角色
        if(user.getRole()==null)
        {user.setRole("PUBLIC");}

        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        // 插入新用户
        boolean success = save(user);
        if (!success) {
            return Result.error("注册失败，请重试");
        }

        //生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getUserId());
        claims.put("username", user.getEmail());
        String taken = JwtUtils.generateToken(claims);

        // 返回登录信息
        LoginInfo loginInfo = new LoginInfo(user.getUserId(), user.getEmail(), user.getRole(),taken);

        return Result.success(loginInfo);
    }

    @Override
    public Result changePassword(LoginChangePassward loi) {
        // 1. 从CurrentHolder中获取用户id
        System.out.println("进入修改用户service");
        Long userId = (long)CurrentHolder.getCurrentId();
        if (userId==null) {
            return Result.error("未知用户");
        }

        try {
            String oldPassword = loi.getOldPassword(); // 旧密码
            String newPassword = loi.getNewPassword(); // 新密码
            String confirmPassword = loi.getConfirmPassword(); // 确认密码

            // 2. 校验新密码和确认密码是否一致
            if (!newPassword.equals(confirmPassword)) {
                return Result.error("两次输入的新密码不一致");
            }

//            // 3. 校验新密码格式
//            if (!Pattern.matches(PASSWORD_REGEX, newPassword)) {
//                return Result.error("密码必须是8-128位，包含大小写字母、数字和特殊字符");
//            }

            // 4. 查询当前用户信息
            User currentUser = baseMapper.selectById(userId);
            if (currentUser == null) {
                return Result.error("用户不存在");
            }

            // 5. 校验旧密码是否正确
            if (!currentUser.getPassword().equals(oldPassword)) {
                return Result.error("原始密码错误");
            }

            System.out.println(newPassword);
            System.out.println(userId);
            // 6. 更新密码
            int rows = baseMapper.updatePasswordById(newPassword,userId);
            System.out.println(newPassword);
            System.out.println(userId);
            if (rows <= 0) {
                return Result.error("密码修改失败");
            }

            return Result.success("密码修改成功");

        } catch (Exception e) {
            //log.error("数据库操作异常", e);
            return Result.error("token 验证失败或操作异常");
        }
    }

    @Autowired
    private EmailApi emailApi;

    @Override
    public Result sendEmail(String email) {
        Validation v = baseMapper.selectByEmailVali(email);
        LocalDateTime now = LocalDateTime.now();
        if(v!=null){
            // 验证码未过期，不能重新发送
            if (now.isBefore(v.getTime())) {
                return Result.error("验证码尚未过期，请勿重复发送");
            } else {
                // 验证码已过期，删除旧记录
                baseMapper.deleteByIdVali(v.getId());
            }
        }
        String code = RandomUtil.randomNumbers(4);
        System.out.println("发送验证码");
        boolean send = emailApi.sendGeneralEmail("验证码", "您的验证码为：" + code, email);
        if (!send) {
            return Result.error("发送验证码失败");
        }
        baseMapper.insertVali(new Validation(null, email, code, now.plusMinutes(10)));
        return Result.success();
    }
} 