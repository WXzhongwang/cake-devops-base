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
    local custom_script=\$3

    if [ -z "$repo_url" ] || [ -z "$webhook_url" ] || [ -z "$custom_script" ]; then
        echo "Usage: \$0 <repository_url> <webhook_url> <custom_maven_command>"
        return 1
    fi

    repo_name=$(basename "$repo_url" | rev | cut -d. -f2- | rev)
    folder_name="$(pwd)/$repo_name"

    # 进入代码目录
    # shellcheck disable=SC2164
    # cd "$folder_name"

#    # 编译构建
#    $MAVEN_HOME_363 clean package -U -DskipTests=true
#
#    # 判断编译是否成功
#    # shellcheck disable=SC2181
#    if [ $? -ne 0 ]; then
#        echo "编译失败"
#        send_notification "编译失败" "failed" "$repo_name" "$webhook_url"
#        exit 1
#    fi
#    echo "【MavenBuild】编译成功..."
    (
        # 使用子shell以隔离cd操作
        cd "$folder_name" || { echo "无法进入目录 $folder_name"; return 1; }

        # 安全替换mvn命令
        safe_custom_script=$(printf "%q" "$custom_script" | sed "s/mvn/$MAVEN_HOME_363/bin/mvn/")

        # 执行构建
        if /bin/bash -c "$safe_custom_script"; then
            echo "【MavenBuild】编译成功..."
            return 0
        else
            echo "编译失败"
            send_notification "编译失败" "failed" "$repo_name" "$webhook_url"
            return 1
        fi
    )
}

# 示例用法
mvn_build "$1" "$2" "$3"
