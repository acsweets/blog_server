@echo off
echo 正在编译Java文件...

javac -d target/classes -cp "src/main/java" src/main/java/com/blog/*.java

if %errorlevel% neq 0 (
    echo 编译失败，请检查代码
    pause
    exit /b 1
)

echo 编译成功！
echo 注意：此项目需要Spring Boot依赖才能运行
echo 建议使用以下方式之一运行：
echo 1. 安装Maven，然后执行: mvn spring-boot:run
echo 2. 在IDE（如IntelliJ IDEA或Eclipse）中导入项目并运行
echo 3. 使用Spring Boot CLI
echo.
echo 项目已创建完成，请参考README.md文件获取详细说明
pause