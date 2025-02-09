#!/bin/bash
# send_notification.sh
# source ~/.bash_profile

# 通过钉钉发送通知
function send_dingding_notification {
    local message=$1
    local webhook_url=$2
    local status=$3
    local app_name=$4
    # shellcheck disable=SC2155
    local date_suffix=$(date "+%Y-%m-%d %H:%M:%S")

    # 根据状态选择字体颜色
    local color=""
    if [ "$status" == "succeed" ]; then
        color="#00FF00"  # 绿色
    else
        color="#FF0000"  # 红色
    fi

    # 使用Markdown格式发送钉钉通知
    curl -H "Content-Type: application/json" -X POST -d '{
        "msgtype": "markdown",
        "markdown": {
            "title": "【COVA】构建通知",
            "text": "# 构建通知\n\n <font color=\"'$color'\"> Cova </font>提醒您:\n\n - 应用名称：['"$app_name"']\n- 时间：['"$date_suffix"']\n- 状态：['"$status"']\n- 备注：['"$message"']"
        }
    }' "$webhook_url"
}

# 发送通知
function send_notification {
    local message=$1
    local status=$2
    local app_name=$3
    local webhook_url=$4

    send_dingding_notification "$message" "$webhook_url" "$status" "$app_name"
}

# 示例用法
send_notification "$1" "$2" "$3" "$4"
