#!/bin/bash
# push_harbor.sh

source ./send_notification.sh

function push_harbor_image {
    echo "【PushImage】开始运行..."
    local repo_url=$1
    local namespace=$2
    local project=$3
    local version=$4

    local HARBOR_URL=$5
    # shellcheck disable=SC2034
    local HARBOR_USERNAME=$6
    local HARBOR_PASSWORD=$7
    local webhook_url=$8

    DOCKER_HOME=/usr/local/bin/docker
    if [ ! -x "$DOCKER_HOME" ]; then
        echo "Docker not found"
        exit 1
    fi

    repo_name=$(basename "$repo_url" | rev | cut -d. -f2- | rev)

    # 登录Harbor
    # shellcheck disable=SC2153
    $DOCKER_HOME login -u "$HARBOR_URER" -p "$HARBOR_PASSWORD" "$HARBOR_URL"

    # 标记镜像
    $DOCKER_HOME tag "$project:$version" "$HARBOR_URL/$namespace/$project:$version"

    # 推送镜像到Harbor
    $DOCKER_HOME push "$HARBOR_URL/$namespace/$project:$version"

    # 判断镜像推送是否成功
    # shellcheck disable=SC2181
    if [ $? -ne 0 ]; then
        echo "推送镜像失败"
        send_notification "推送镜像失败" "failed" "$repo_name" "$webhook_url"
        exit 1
    fi
   echo "【PushImage】推送镜像成功..."
}

# 示例用法
push_harbor_image "$1" "$2" "$3" "$4" "$5" "$6" "$7" "$8"
