# VSCode 运行指南

## 必需的VSCode扩展
请安装以下扩展：
1. **Extension Pack for Java** (Microsoft) - Java开发必备
2. **Spring Boot Extension Pack** (VMware) - Spring Boot支持
3. **Maven for Java** (Microsoft) - Maven支持

## 运行方式

### 方式1：使用调试功能（推荐）
1. 按 `F5` 或点击"运行和调试"
2. 选择"启动博客服务"
3. 服务将在 http://localhost:8080 启动

### 方式2：使用Maven任务
1. 按 `Ctrl+Shift+P` 打开命令面板
2. 输入"Tasks: Run Task"
3. 选择"maven: spring-boot:run"

### 方式3：直接运行主类
1. 打开 `BlogApplication.java` 文件
2. 点击main方法上方的"Run"按钮
3. 或右键选择"Run Java"


## 常见问题
- 如果Maven命令不可用，请确保已安装Maven并配置环境变量
- 如果Java版本不匹配，请检查VSCode的Java设置
- 确保端口8080未被占用