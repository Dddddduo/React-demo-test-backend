-- 新建一个数据库
CREATE DATABASE IF NOT EXISTS access;

-- 使用数据库
USE access;

-- 创建表
CREATE TABLE IF NOT EXISTS User
(
    id         bigint AUTO_INCREMENT PRIMARY KEY, -- 自增ID
    Name       VARCHAR(100) NOT NULL,
    Gender     VARCHAR(10)  NOT NULL,          -- '1男 Male', '2女 Female',
    Mobile     VARCHAR(15)  NOT NULL,
    Email      VARCHAR(100) NOT NULL,
    Status     VARCHAR(10)  NOT NULL,          -- '0禁用', '1可用'
    Created_At TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Update_At TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Is_delect tinyint DEFAULT 0 NOT NULL   -- '0未删除','1删除'

);


-- 写入数据
INSERT INTO User (Name, Gender, Mobile, Email, Status)

-- 因为给出的图片 主键不是连续的 所以我这边选择手动添加数据

# VALUES ('Alice', '2', '1234567890', 'alice@example.com', '1'),
#        ('Bob', '1', '0987654321', 'bob@example.com', '1'),
#        ('Charlie', '1', '2345678901', 'charlie@example.com', '1'),
#        ('Charlie', '1', '2345678901', 'charlie@example.com', '1');

-- 将邮件键设置为唯一的
ALTER TABLE user ADD CONSTRAINT Email UNIQUE (Email);

-- 将更新字段提供自动填充
ALTER TABLE user MODIFY Update_At DATETIME DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE user DROP INDEX Email;



