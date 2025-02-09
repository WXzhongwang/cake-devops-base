#!/bin/bash
# build_image.sh

source conf/cake-sample.conf
source send_notification.sh


function build_image {
    echo "【BuildImage】开始运行..."
    local repo_url=$1
    local project=$2
    local version=$3
    local webhook_url=$4
    local env=$5

    DOCKER_HOME=/usr/local/bin/docker
    if [ ! -x "$DOCKER_HOME" ]; then
        echo "Docker not found"
        exit 1
    fi

    repo_name=$(basename "$repo_url" | rev | cut -d. -f2- | rev)
    folder_name="$(pwd)/$repo_name"

    # 进入代码目录
    cd "$folder_name" || { echo "Error: Directory $folder_name does not exist."; exit 1; }

    # 输出当前所在路径
    echo "Current directory: $(pwd)"

    # 根据环境选择 Dockerfile_test
    local dockerfile="APP-META/Dockerfile"
    if [ "$env" != "dev" ]; then
        dockerfile="APP-META/Dockerfile_$env"
    fi

    # 检查 Dockerfile_test 是否存在
    if [ ! -f "$dockerfile" ] || [ ! -r "$dockerfile" ]; then
        echo "错误: Dockerfile '$dockerfile' 不存在或不可读。"
        send_notification "Dockerfile '$dockerfile' 不存在或不可读" "error" "$repo_name" "$webhook_url"
        exit 1
    fi

    # 生成镜像
    echo "【BuildImage】开始构建镜像..."
    # 输出下环境
    echo "当前环境为：$env"
    if [ "$env" = "dev" ]; then
        # 输出下完整指令
        echo "docker build -f $dockerfile -t $project:$version ."
        $DOCKER_HOME build -f "$dockerfile" -t "$project:$version" .
    else
        # 输出下完整指令
        echo "docker build --build-arg ENV=$env -f $dockerfile -t $project:$version ."
        $DOCKER_HOME build --build-arg ENV="$env" -f "$dockerfile" -t "$project:$version" .
    fi

    # 判断镜像生成是否成功
    # shellcheck disable=SC2181
    if [ $? -ne 0 ]; then
        echo "镜像构建失败"
        send_notification "镜像构建失败" "failed" "$repo_name" "$webhook_url"
        exit 1
    fi
    echo "【BuildImage】镜像构建成功..."
}

build_image "$1" "$2" "$3" "$4" "$5"
