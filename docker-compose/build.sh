#!/bin/bash
# set -e

source conf/cake-sample.conf

# 发送钉钉通知
function send_dingding_notification {
  local message=$1
  local webhook_url=$2
  local status=$3
  local app_name=$4
  local date_suffix=$(date "+%Y-%m-%d %H:%M:%S")
  # 使用Markdown格式发送钉钉通知
  curl -H "Content-Type: application/json" -X POST -d '{
    "msgtype": "markdown",
    "markdown": {
      "title": "【COVA】构建通知",
      "text": "# 构建通知\n\n <font color=\"#FF0000\"> Cova </font>提醒您:\n\n - 应用名称：['"$app_name"']\n- 时间：['"$date_suffix"']\n- 状态：['"$status"']\n- 备注：['"$message"']"
    }
  }' "$webhook_url"
}

# 拉取代码
function checkout {
  local repo_url=$1
  local branch_name=$2
  local folder_name=$3

  # 拉取远程Git仓库代码
  git clone -b "$branch_name" "$repo_url" "$folder_name"
}

# 编译打包
function mvn_build {
  local folder_name=$1

  # shellcheck disable=SC2164
  cd "$folder_name"
  # 编译构建
  $MAVEN_HOME_363 clean package -U -DskipTests=true

  # 判断编译是否成功
  if [ $? -ne 0 ]; then
    echo "Compilation failed"
    return 1
  fi
}

# SonarQube扫描
function sonar_scan {
  local sonar_scan=$1
  local sonar_url=$2
  local sonar_token=$3
  local repo_name=$4

  # 判断是否执行SonarQube扫描任务
  if [ "$sonar_scan" == "true" ]; then
    # 执行SonarQube扫描任务
    # shellcheck disable=SC2153
    $SONAR_SCAN_HOME \
      -Dsonar.projectKey="$repo_name" \
      -Dsonar.sources=. \
      -Dsonar.host.url="$SONAR_URL" \
      -Dsonar.login="$SONAR_TOKEN" \
      -Dsonar.java.binaries=*/*/classes

    # 判断SonarQube扫描是否成功
    if [ $? -ne 0 ]; then
      echo "SonarQube scan failed"
      return 1
    fi
  fi
}

# 构建镜像
function build_image {
  local image_name=$1

  # 生成镜像
  $DOCKER_HOME build -t "$image_name" .

  # 判断
  # 判断镜像生成是否成功
  if [ $? -ne 0 ]; then
    echo "Failed to build image"
    return 1
  fi
}

# 推送镜像到Harbor
function push_image {
  local harbor_url=$1
  local harbor_username=$2
  local harbor_password=$3
  local image_name=$4

  # 登录Harbor
  # shellcheck disable=SC2153
  $DOCKER_HOME login -u "$HARBOR_URER" -p "$HARBOR_PASSWORD" "$HARBOR_URL"

  # 标记镜像
  $DOCKER_HOME tag "$image_name" "$HARBOR_URL/$image_name"

  # 推送镜像到Harbor
  $DOCKER_HOME push "$HARBOR_URL/$image_name"

  # 判断镜像推送是否成功
  if [ $? -ne 0 ]; then
    echo "Failed to push image"
    return 1
  fi
}

# 主函数
function main {
  local repo_url=$1
  local branch_name=$2
  local sonar_scan=$3
  local image_name=$4
  local dingtalk_webhook_url=$5

  # 发送钉钉通知
  function send_notification {
    local message=$1
    local status=$2
    local app_name=$3

    send_dingding_notification "$message" "$dingtalk_webhook_url" "$status" "$app_name"
  }

  # 设置日期格式
  date_suffix=$(date "+%Y%m%d_%H%M%S")
  # 创建文件夹，解析出仓库名称
  repo_name=$(basename "$repo_url" | rev | cut -d. -f2- | rev)
  folder_name="${repo_name}/${date_suffix}"
  echo "$folder_name"
  mkdir -p "$folder_name"

  # 拉取代码
  checkout "$repo_url" "$branch_name" "$folder_name"

  # 判断拉取代码是否成功
  if [ $? -ne 0 ]; then
    send_notification "拉取代码失败" "failed" "$repo_name"
    exit 1
  fi

  # 编译打包
  mvn_build "$folder_name"

  # 判断编译打包是否成功
  if [ $? -ne 0 ]; then
    send_notification "编译打包失败" "failed" "$repo_name"
    exit 1
  fi

  # SonarQube扫描
  sonar_scan "$sonar_scan" "$sonar_url" "$sonar_token" "$repo_name"

  # 判断SonarQube扫描是否成功
  if [ $? -ne 0 ]; then
    send_notification "SonarQube扫描失败" "failed" "$repo_name"
    exit 1
  fi

  # 构建镜像
  build_image "$image_name"

  # 判断构建镜像是否成功
  if [ $? -ne 0 ]; then
    send_notification "构建镜像失败" "failed" "$repo_name"
    exit 1
  fi

  # 推送镜像到Harbor
  push_image "$harbor_url" "$harbor_username" "$harbor_password" "$image_name"

  # 判断推送镜像是否成功
  if [ $? -ne 0 ]; then
    send_notification "推送镜像失败" "failed" "$repo_name"
    exit 1
  fi

  # 发送钉钉通知
  send_notification "构建和推送成功" "succeed" "$repo_name"

  # 成功退出
  echo "Build and push completed successfully"
  exit 0
}

# 启动
main "$1" "$2" "$3" "$4" "$5"