#!/bin/bash
# maven build.sh

source conf/cake-sample.conf
source send_notification.sh
# shellcheck disable=SC1090
source ~/.bash_profile

function mvn_build {
    echo "【MavenBuild】开始运行..."
    local repo_url=$1
    local webhook_url=$2

    repo_name=$(basename "$repo_url" | rev | cut -d. -f2- | rev)
    folder_name="$(pwd)/$repo_name"

    # 进入代码目录
    # shellcheck disable=SC2164
    cd "$folder_name"

    # 编译构建
    $MAVEN_HOME_363 clean package -U -DskipTests=true

    # 判断编译是否成功
    # shellcheck disable=SC2181
    if [ $? -ne 0 ]; then
        echo "编译失败"
        send_notification "编译失败" "failed" "$repo_name" "$webhook_url"
        exit 1
    fi
    echo "【MavenBuild】编译成功..."
}

# 示例用法
mvn_build "$1" "$2"
