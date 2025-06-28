CREATE TABLE users (
                       user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       role ENUM('ENV_OFFICER', 'CITY_MANAGER', 'PUBLIC') NOT NULL,
                       email VARCHAR(100),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `validation`  (
                               `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
                               `email` varchar(255)  NOT NULL COMMENT '用户邮箱',
                               `code` varchar(255)   NOT NULL COMMENT '验证码',
                               `time` timestamp(0) NULL DEFAULT NULL COMMENT '过期时间'
)
