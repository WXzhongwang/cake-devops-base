#!/bin/bash

set -e

# 检查命令行参数
if [ $# -lt 2 ] || [ $# -gt 3 ]; then
    echo "Usage: $0 <APP_NAME> {start|stop|restart} [spring_profiles_active]" >&2
    exit 2 # 参数错误
fi

# 操作参数
APP_NAME=$1
ACTION=$2
SPRING_PROFILES_ACTIVE=${3:-dev}

echo "APP_NAME: ${APP_NAME}"
echo "ACTION: ${ACTION}"
echo "Spring Profiles Active: ${SPRING_PROFILES_ACTIVE}"

# 定义路径
JAR_FILE="/home/admin/${APP_NAME}/cake-devops-service.jar"
MAIN_CLASS="com.rany.cake.devops.base.CakeDevopsBaseApplication"

# 检查 JAR 文件是否存在
if [ ! -f "$JAR_FILE" ]; then
    echo "ERROR: JAR file does not exist at path: $JAR_FILE" >&2
    exit 1
fi

# 定义 JVM 选项
JAVA_OPTS=(
    "-XX:+UseContainerSupport"
    "-XX:InitialRAMPercentage=50.0"
    "-XX:MaxRAMPercentage=75.0"
    "-Xloggc:/home/admin/gc.log"
    "-XX:+PrintGCDetails"
    "-XX:+PrintGCDateStamps"
)

# 定义启动命令
START_CMD="java ${JAVA_OPTS[*]} -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -jar $JAR_FILE"

# 检查应用是否正在运行
is_running() {
    pgrep -f "$MAIN_CLASS" > /dev/null
    return $?
}

# 启动应用
start_app() {
    echo "Starting application..."
    if is_running; then
        echo "Application is already running."
    else
        exec $START_CMD
    fi
}

# 停止应用
stop_app() {
    echo "Stopping application..."
    if is_running; then
        pkill -f "$MAIN_CLASS"
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

# 执行操作
case "$ACTION" in
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
        echo "Usage: $0 <APP_NAME> {start|stop|restart} [spring_profiles_active]" >&2
        exit 1
        ;;
esac