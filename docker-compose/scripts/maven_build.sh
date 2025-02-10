#!/bin/bash
# maven build.sh

source ./send_notification.sh

function mvn_build {
    echo "【MavenBuild】开始运行..."
    local repo_url=$4
    local webhook_url=$5
    local custom_script=$1
    local mvn_version=$2
    local java_version=$3

    # 打印接收到的参数值，便于调试
    echo "【Debug】仓库 URL: $repo_url"
    echo "【Debug】Webhook URL: $webhook_url"
    echo "【Debug】自定义 Maven 命令: $custom_script"
    echo "【Debug】Maven 版本: $mvn_version"
    echo "【Debug】Java 版本: $java_version"

    local INSTALL_PATH=/usr/local/src/software

    local MAVEN_HOME_363="$INSTALL_PATH/apache-maven_$mvn_version/bin/mvn"
    echo "临时设置JAVA_HOME"
    if [ -n "$JAVA_HOME" ] && [ -d "$JAVA_HOME" ]; then
        echo "JAVA_HOME 已存在，无需临时设置"
    else
        echo "临时设置JAVA_HOME"
        export JAVA_HOME="$INSTALL_PATH/jdk_$java_version"
        export PATH="$JAVA_HOME/bin:$PATH"
    fi


    if [ -z "$repo_url" ] || [ -z "$webhook_url" ] || [ -z "$custom_script" ]; then
        echo "Usage: \$0 <repository_url> <webhook_url> <custom_maven_command>"
        return 1
    fi

    repo_name=$(basename "$repo_url" | rev | cut -d. -f2- | rev)
    folder_name="$(pwd)/$repo_name"



    # 进入代码目录
    cd "$folder_name" || { echo "无法进入目录 $folder_name"; return 1; }

    # 安全替换mvn命令
    # 安全替换mvn命令
    safe_custom_script="${custom_script//mvn/$MAVEN_HOME_363} -s ~/.m2/settings.xml"
    # 输出完整指令
    echo "执行指令: $safe_custom_script"


    # 执行构建
    if eval "$safe_custom_script"; then
        echo "【MavenBuild】编译成功..."
        return 0
    else
        echo "编译失败"
        send_notification "编译失败" "failed" "$repo_name" "$webhook_url"
        return 1
    fi
}

# 示例用法
mvn_build "$1" "$2" "$3" "$4" "$5"
