#!/bin/bash
# build_image.sh

source conf/cake-sample.conf
source send_notification.sh
source ~/.bash_profile

function build_image {
    echo "【BuildImage】开始运行..."
    local repo_url=$1
    local project=$2
    local version=$3
    local webhook_url=$4

    repo_name=$(basename "$repo_url" | rev | cut -d. -f2- | rev)
    folder_name="$(pwd)/$repo_name"

    # 进入代码目录
    # shellcheck disable=SC2164
    cd "$folder_name"

    # 生成镜像
    $DOCKER_HOME build -t "$project:$version" .

    # 判断镜像生成是否成功
    # shellcheck disable=SC2181
    if [ $? -ne 0 ]; then
        echo "镜像构建失败"
        send_notification "镜像构建失败" "failed" "$repo_name" "$webhook_url"
        exit 1
    fi
}

build_image "$1" "$2" "$3" "$4"
