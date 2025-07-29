@echo off
echo 正在编译项目...
javac -cp "lib/*" -d target/classes src/main/java/com/blog/*.java
if %errorlevel% neq 0 (
    echo 编译失败
    pause
    exit /b 1
)

echo 正在启动服务...
java -cp "target/classes;lib/*" com.blog.BlogApplication
pause