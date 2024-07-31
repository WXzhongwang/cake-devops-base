#!/bin/bash

# 检查是否运行在容器内
if [[ "$(type -t docker)" == "function" ]]; then
    IN_DOCKER=true
else
    IN_DOCKER=false
fi

# 定义日志文件
LOGFILE=/app/server_std_out.log

# 定义 Java 主类和 jar 文件路径
MAIN_CLASS=com.example.CakeDevopsServiceApplication
JAR_FILE=/app/cake-devops-service.jar

# 定义 JVM 选项
JAVA_OPTS="-Xms512m -Xmx1024m \
            -Xloggc:/home/admin/gc.log \
            -XX:+PrintGCDetails \
            -XX:+PrintGCDateStamps \
            -XX:+PrintGCTimeStamps \
            -XX:+PrintGCCause \
            -XX:+PrintGCApplicationStoppedTime \
            -XX:+UseGCLogFileRotation \
            -XX:NumberOfGCLogFiles=10 \
            -XX:GCLogFileSize=100M"

# 定义 Spring profiles active
SPRING_PROFILES_ACTIVE=${ENV:-dev}

# 定义启动命令
START_CMD="java $JAVA_OPTS -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -jar $JAR_FILE"

# 检查应用是否正在运行
is_running() {
    if $IN_DOCKER; then
        pgrep -f "$MAIN_CLASS"
    else
        ps aux | grep "[j]ava.*$MAIN_CLASS"
    fi
}

# 启动应用
start_app() {
    echo "Starting application..."
    if is_running; then
        echo "Application is already running."
    else
        exec $START_CMD >> $LOGFILE 2>&1 &
        echo "Application started."
    fi
}

# 停止应用
stop_app() {
    echo "Stopping application..."
    if is_running; then
        if $IN_DOCKER; then
            pkill -f "$MAIN_CLASS"
        else
            killall -q java
        fi
        echo "Application stopped."
    else
        echo "Application is not running."
    fi
}

# 重启应用
restart_app() {
    stop_app
    start_app
}

# 执行启动命令
case "$1" in
    start)
        start_app
        ;;
    stop)
        stop_app
        ;;
    restart)
        restart_app
        ;;
    *)
        echo "Usage: $0 {start|stop|restart}"
        exit 1
        ;;
esac

exit 0
