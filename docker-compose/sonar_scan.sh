#!/bin/bash
# sonar_scan.sh

source conf/cake-sample.conf
source send_notification.sh
source ~/.bash_profile

function sonar_scan {
    echo "【SonarScan】开始运行..."
    local repo_url=$1
    local webhook_url=$2

    repo_name=$(basename "$repo_url" | rev | cut -d. -f2- | rev)
    folder_name="$(pwd)/$repo_name"

    # 进入代码目录
    # shellcheck disable=SC2164
    cd "$folder_name"

    # 执行SonarQube扫描任务
    $SONAR_SCAN_HOME \
        -Dsonar.projectKey="$repo_name" \
        -Dsonar.sources=. \
        -Dsonar.host.url="$SONAR_URL" \
        -Dsonar.login="$SONAR_TOKEN" \
        -Dsonar.java.binaries=*/*/classes

    # 判断SonarQube扫描是否成功
    # shellcheck disable=SC2181
    if [ $? -ne 0 ]; then
        echo "SonarQube扫描失败"
           send_notification "SonarQube扫描失败" "failed" "$repo_name" "$webhook_url"
        exit 1
    fi
    echo "【SonarScan】扫描成功..."
}

# 示例用法
sonar_scan "$1" "$2"
