-- 创建数据库
CREATE DATABASE IF NOT EXISTS blog_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE blog_db;

-- 创建用户（如果需要）
-- CREATE USER 'blog_user'@'localhost' IDENTIFIED BY 'blog123456';
-- GRANT ALL PRIVILEGES ON blog_db.* TO 'blog_user'@'localhost';
-- FLUSH PRIVILEGES;