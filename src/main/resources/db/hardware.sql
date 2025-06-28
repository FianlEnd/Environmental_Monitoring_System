# -- 温度表
# CREATE TABLE temperature (
#                              id INT AUTO_INCREMENT PRIMARY KEY,
#                              payload VARCHAR(100) NOT NULL,
#                              record_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
# );
#
# -- 湿度表
# CREATE TABLE humidity (
#                           id INT AUTO_INCREMENT PRIMARY KEY,
#                           payload VARCHAR(100) NOT NULL,
#                           record_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
# );
#
# -- 可燃气体表
# CREATE TABLE combustible_gas (
#                                  id INT AUTO_INCREMENT PRIMARY KEY,
#                                  payload VARCHAR(100) NOT NULL,
#                                  record_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
# );

-- 数据表
CREATE TABLE data (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      temperature DOUBLE NOT NULL COMMENT '温度值',
                      humidity DOUBLE NOT NULL COMMENT '湿度值',
                      combustible_gas DOUBLE NOT NULL COMMENT '可燃气体值',
                      record_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间'
);
