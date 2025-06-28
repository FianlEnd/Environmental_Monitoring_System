package com.example.mybatisdemo.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author mijiupro
 */
@Component
@Slf4j
public class EmailApi {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from ;// 发件人

    /**
     * 发送纯文本的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @return 是否成功
     */
    @SneakyThrows(Exception.class)
    public boolean sendGeneralEmail(String subject, String content, String... to){
        // 创建邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        // 设置收件人
        message.setTo(to);
        // 设置邮件主题
        message.setSubject(subject);
        // 设置邮件内容
        message.setText(content);
        // 发送邮件
        mailSender.send(message);

        return true;
    }

}
