#!/bin/bash
# checkout.sh

source conf/cake-sample.conf
source send_notification.sh

function checkout {
    echo "【Checkout】开始运行..."
    local repo_url=$1
    local branch_name=$2
    local webhook_url=$3

    repo_name=$(basename "$repo_url" | rev | cut -d. -f2- | rev)
    folder_name="$(pwd)/$repo_name"

    # 拉取远程Git仓库代码
    git clone -b "$branch_name" "$repo_url" "$folder_name"
    # shellcheck disable=SC2181
    if [ $? -ne 0 ]; then
        echo "代码拉取失败"
        # shellcheck disable=SC2154
        send_notification "代码拉取失败" "failed" "$repo_name" "$webhook_url"
        exit 1
    fi
}

# 示例用法
checkout "$1" "$2" "$3"
