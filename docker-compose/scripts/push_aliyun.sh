#!/bin/bash
# push_aliyun.sh

source ./send_notification.sh


function push_aliyun_image {
    echo "【PushAliyunImage】开始运行..."
    local repo_url=$1
    local namespace=$2
    local project=$3
    local version=$4
    local ALIYUN_ACR_URL=$5
    local ALIYUN_ACR_USER_NAME=$6
    local ALIYUN_ACR_USER_PASSWORD=$7
    local webhook_url=$8
    echo "repo_url: $repo_url"
    echo "namespace: $namespace"
    echo "project: $project"
    echo "version: $version"
    echo "ALIYUN_ACR_URL: $ALIYUN_ACR_URL"
    echo "ALIYUN_ACR_USER_NAME: $ALIYUN_ACR_USER_NAME"
    echo "webhook_url: $webhook_url"

    local DOCKER_HOME=/usr/bin/docker
    if [ ! -x "$DOCKER_HOME" ]; then
        echo "Docker not found"
        exit 1
    fi

    repo_name=$(basename "$repo_url" | rev | cut -d. -f2- | rev)
    echo "$repo_name"

    # 登录ACR
    $DOCKER_HOME login -u "$ALIYUN_ACR_USER_NAME" -p "$ALIYUN_ACR_USER_PASSWORD" "$ALIYUN_ACR_URL"  | tee -a push_aliyun.log

    # 标记镜像
    $DOCKER_HOME tag "$project:$version" "$ALIYUN_ACR_URL/$namespace/$project:$version"  | tee -a push_aliyun.log

    # 推送镜像到阿里云
    if $DOCKER_HOME push "$ALIYUN_ACR_URL/$namespace/$project:$version" | tee -a push_aliyun.log; then
        echo "【PushAliyunImage】推送阿里云镜像成功..."
    else
        echo "推送镜像失败"
        echo "调用 send_notification: message='推送镜像失败', status='failed', app_name='$project', webhook_url='$webhook_url'"
        send_notification "推送镜像失败" "failed" "$project" "$webhook_url"
        exit 1
    fi
    echo "【PushAliyunImage】推送阿里云镜像成功..."
}

# 示例用法
push_aliyun_image "$1" "$2" "$3" "$4" "$5" "$6" "$7" "$8"
