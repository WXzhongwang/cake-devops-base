#!/bin/sh

set -e

# 检查命令行参数
if [ $# -lt 2 ] || [ $# -gt 3 ]; then
    echo "Usage: $0 {start|stop|restart} <app_name> [spring_profiles_active]" >&2
    exit 2 # bad usage
fi

# 操作
ACTION=$1
APP_NAME=$2
SPRING_PROFILES_ACTIVE=${3:-dev}

echo "APP_NAME: ${APP_NAME}"
echo "ACTION: ${ACTION}"
echo "Spring Profiles Active: ${SPRING_PROFILES_ACTIVE}"

# 定义 PID 文件路径
PID_FILE="/home/admin/${APP_NAME}/app.pid"

# 确保 PID 文件目录存在
mkdir -p "$(dirname "$PID_FILE")"

# 定义 Java 主类和 jar 文件路径
# MAIN_CLASS="com.rany.cake.devops.base.CakeDevopsBaseApplication"
JAR_FILE="/home/admin/${APP_NAME}/cake-devops-service.jar"

# 检查 JAR 文件是否存在
if [ ! -f "$JAR_FILE" ]; then
    echo "JAR file does not exist at path: $JAR_FILE" >&2
    exit 1
fi

# 定义 JVM 选项
JAVA_OPTS="-Xms512m -Xmx1024m -Xloggc:/home/admin/${APP_NAME}/gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintGCCause -XX:+PrintGCApplicationStoppedTime -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"

# 定义启动命令
START_CMD="$JAVA_HOME/bin/java $JAVA_OPTS -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -jar $JAR_FILE"

# 检查应用是否正在运行
is_running() {
    if [ -f "$PID_FILE" ]; then
        pid=$(cat "$PID_FILE")
        if ps -p "$pid" > /dev/null; then
            return 0
        else
            rm -f "$PID_FILE"
        fi
    fi
    return 1
}

# 启动应用
start_app() {
    echo "Starting application..."
    if is_running; then
        echo "Application is already running."
    else
        echo "Starting application, exec java cmd..."
        nohup $START_CMD > /dev/stdout 2>&1 &
        echo $! > "$PID_FILE"
        echo "Application started with PID $(cat $PID_FILE)."
    fi
}

# 停止应用
stop_app() {
    echo "Stopping application..."
    if is_running; then
        # shellcheck disable=SC2046
        kill $(cat "$PID_FILE")
        rm -f "$PID_FILE"
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
        echo "Usage: $0 {start|stop|restart} <app_name> [spring_profiles_active]" >&2
        exit 1
        ;;
esac

exit 0