#!/bin/bash

# 检查是否运行在容器内
IN_DOCKER=$(command -v docker &> /dev/null && echo true || echo false)

# 定义日志文件
LOGFILE="/home/admin/${APP_NAME}/server_std_out.log"
DEPLOY_LOG="/home/admin/${APP_NAME}/deploy.log"

# 定义 Java 主类和 jar 文件路径
MAIN_CLASS="com.rany.cake.devops.base.CakeDevopsBaseApplication"
JAR_FILE="/home/admin/${APP_NAME}/cake-devops-service.jar"

# 定义 JVM 选项
JAVA_OPTS=(
    "-Xms512m -Xmx1024m"
    "-Xloggc:/home/admin/gc.log"
    "-XX:+PrintGCDetails"
    "-XX:+PrintGCDateStamps"
    "-XX:+PrintGCTimeStamps"
    "-XX:+PrintGCCause"
    "-XX:+PrintGCApplicationStoppedTime"
    "-XX:+UseGCLogFileRotation"
    "-XX:NumberOfGCLogFiles=10"
    "-XX:GCLogFileSize=100M"
)

# 定义 Spring profiles active
SPRING_PROFILES_ACTIVE="${ENV:-dev}"

# 定义启动命令
START_CMD="java ${JAVA_OPTS[*]} -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -jar $JAR_FILE"

# 检查应用是否正在运行
is_running() {
    local pipe_status=()
    if [[ $IN_DOCKER == true ]]; then
        pgrep -f "$MAIN_CLASS"
        pipe_status=($?)
    else
        ps aux | grep -P "[j]ava.*$MAIN_CLASS" | grep -v grep
        pipe_status=($?)
    fi
    check_first_pipe_exit_code "${pipe_status[@]}"
}

# 检查管道中第一个命令的退出状态
check_first_pipe_exit_code() {
    if [[ $1 -ne 0 ]]; then
        echo "Error in pipeline, first command failed with status $1"
        exit $1
    fi
}

# 启动应用
start_app() {
    echo "Starting application..." | tee -a "$DEPLOY_LOG"
    if is_running | grep -q .; then
        echo "Application is already running." | tee -a "$DEPLOY_LOG"
    else
      # 使用nohup来让Java应用在后台运行，并使用tee同时输出到控制台和日志文件
      nohup $START_CMD | tee -a "$LOGFILE" &
      echo "Application started." | tee -a "$DEPLOY_LOG"
      # 输出应用PID到另一个文件，方便后续操作
      echo $! > /home/admin/${APP_NAME}/app.pid
    fi
}

# 停止应用
stop_app() {
    echo "Stopping application..." | tee -a "$DEPLOY_LOG"
    if is_running | grep -q .; then
        if [[ $IN_DOCKER == true ]]; then
            pkill -f "$MAIN_CLASS"
        else
            ps aux | grep -P "[j]ava.*$MAIN_CLASS" | awk '{print $2}' | xargs kill
        fi
        echo "Application stopped." | tee -a "$DEPLOY_LOG"
    else
        echo "Application is not running." | tee -a "$DEPLOY_LOG"
    fi
}

# 重启应用
restart_app() {
    stop_app
    start_app
}

# 检查命令行参数
if [ $# -lt 1 ]; then
    usage
    exit 2 # bad usage
fi

# 脚本名称
PROGRAM_NAME=$0
# 操作
ACTION=$1

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
        echo "Usage: $0 {start|stop|restart}" >&2
        exit 1
        ;;
esac

exit 0
