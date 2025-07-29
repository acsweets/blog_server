# Blog Server API

## 项目说明
这是一个基于Spring Boot的博客后台服务，提供文章的增删改查接口。

## 运行方式

### 方式1：使用Maven（推荐）
```bash
# 安装Maven后执行
mvn spring-boot:run
```

### 方式2：使用IDE
直接在IDE中运行 `BlogApplication.java` 主类

## API接口

服务启动后访问地址：http://localhost:8080

### 接口文档
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- API文档JSON: http://localhost:8080/v3/api-docs

### 认证接口
- POST /api/auth/login - 用户登录
- POST /api/auth/register - 用户注册

### 文章接口
- GET /api/articles - 获取所有文章（无需认证）
- GET /api/articles/{id} - 获取指定文章（无需认证）
- POST /api/articles - 创建文章（需要认证）
- PUT /api/articles/{id} - 更新文章（需要认证）
- DELETE /api/articles/{id} - 删除文章（需要认证）

### 使用Swagger文档
1. 启动服务后访问 http://localhost:8080/swagger-ui/index.html
2. 点击“用户认证”模块先注册和登录
3. 复制登录返回的token
4. 点击右上角“Authorize”按钮，输入：Bearrer {token}
5. 现在可以测试所有需要认证的接口
   Swagger UI: http://localhost:8080/swagger-ui/index.html

   API文档: http://localhost:8080/v3/api-docs

### 请求示例

用户注册：
```json
POST /api/auth/register
{
  "username": "admin",
  "password": "123456"
}
```

用户登录：
```json
POST /api/auth/login
{
  "username": "admin",
  "password": "123456"
}
```

创建文章（需要在Header中添加Authorization: Bearer {token}）：
```json
POST /api/articles
{
  "title": "我的第一篇博客",
  "content": "这是文章内容...",
  "author": "作者名"
}
```

更新文章：
```json
PUT /api/articles/1
{
  "title": "更新后的标题",
  "content": "更新后的内容...",
  "author": "作者名"
}
```

## 数据库
使用MySQL数据库，需要先创建数据库：
1. 启动MySQL服务
2. 执行 `mysql-setup.sql` 脚本创建数据库
3. 确保MySQL用户名密码为 /123456（或修改application.properties中的配置）
4. DROP USER 'blog_user'@'localhost'; (删除用户)
