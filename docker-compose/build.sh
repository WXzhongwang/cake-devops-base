#!/bin/bash



# 发送钉钉通知
function send_dingding_notification {
  message=$1
  webhook_url=$2

  # 使用Markdown格式发送钉钉通知
  curl -H "Content-Type: application/json" -X POST -d '{
    "msgtype": "markdown",
    "markdown": {
      "title": "构建通知",
      "text": "'"$message"'"
    }
  }' $webhook_url
}

# 设置日期格式
date_suffix=$(date "+%Y%m%d_%H%M%S")

# 将所有参数存储到变量中
repo_url=$1
branch_name=$2
sonar_scan=$3
sonar_url=$4
sonar_token=$5
image_name=$6
harbor_url=$7
harbor_username=$8
harbor_password=${9}
dingding_webhook=${10}

# 从仓库URL中提取仓库名称
repo_name=$(basename "$repo_url" | rev | cut -d. -f2- | rev)

# 创建带有仓库名称和日期后缀的文件夹
folder_name="${repo_name}/${date_suffix}"
echo $folder_name
mkdir -p $folder_name

# 进入文件夹
cd $folder_name

# 拉取远程Git仓库代码
git clone -b "$branch_name" "$repo_url" .

# 切换到仓库目录
cd $repo_name

# 编译构建
/usr/local/software/apache-maven-3.6.3/bin/mvn clean package -U -DskipTests=true

# 判断编译是否成功
if [ $? -ne 0 ]; then
  echo "Compilation failed"
  send_dingding_notification "构建失败" $dingding_webhook
  exit 1
fi

# 判断是否执行SonarQube扫描任务
if [ "$sonar_scan" == "true" ]; then
  # 执行SonarQube扫描任务
  sonar-scanner \
    -Dsonar.projectKey=$repo_name \
    -Dsonar.sources=. \
    -Dsonar.host.url=$sonar_url \
    -Dsonar.login=$sonar_token

  # 判断SonarQube扫描是否成功
  if [ $? -ne 0 ]; then
    echo "SonarQube scan failed"
    send_dingding_notification "SonarQube扫描失败" $dingding_webhook
    exit 1
  fi
fi

# 生成镜像
docker build -t $image_name .

# 判断镜像生成是否成功
if [ $? -ne 0 ]; then
  echo "Failed to build image"
  send_dingding_notification "镜像生成失败" $dingding_webhook
  exit 1
fi

# 登录Harbor
docker login -u $harbor_username -p $harbor_password $harbor_url

# 标记镜像
docker tag $image_name $harbor_url/$image_name

# 推送镜像到Harbor
docker push $harbor_url/$image_name

# 判断镜像推送是否成功
if [ $? -ne 0 ]; then
  echo "Failed to push image"
  send_dingding_notification "镜像推送失败" $dingding_webhook
  exit 1
fi

# 发送钉钉通知
send_dingding_notification "构建和推送成功" $dingding_webhook

# 成功退出
echo "Build and push completed successfully"

exit 0