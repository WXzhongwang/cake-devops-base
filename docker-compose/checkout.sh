#!/bin/bash
# checkout.sh

source conf/cake-sample.conf
source send_notification.sh
# shellcheck disable=SC1090
source ~/.bash_profile

function checkout {
    echo "【Checkout】开始运行..."
    local repo_url=$1
    local branch_name=$2
    local webhook_url=$3

    repo_name=$(basename "$repo_url" | rev | cut -d. -f2- | rev)
    folder_name="$(pwd)/$repo_name"

    # 拉取远程Git仓库代码
    # 拉取远程 Git 仓库代码，将标准错误输出合并到标准输出
    clone_output=$(git clone -b "$branch_name" "$repo_url" "$folder_name" 2>&1)

    # 获取 git clone 的返回码
    exit_code=$?
    # shellcheck disable=SC2181
    if [ $exit_code -ne 0 ]; then
        echo "Git Clone Error: $clone_output"
        # shellcheck disable=SC2154
        send_notification "代码拉取失败" "failed" "$repo_name" "$webhook_url"
        exit 1
    fi
    echo "$clone_output"
    echo "【Checkout】代码拉取完成..."
}

# 示例用法
checkout "$1" "$2" "$3"
