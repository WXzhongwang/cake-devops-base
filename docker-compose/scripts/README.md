# 工具包

重新运行生成前记得赋予执行权限:
chmod +x 00_create_tool_tar.sh

```shell
##  第0步
chmod +x 00_create_tool_tar.sh
##  第1步
./00_create_tool_tar.sh
```

打包机配置镜像加速地址

```shell
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
"registry-mirrors": ["https://ozfe4klh.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker

```

集群创建镜像拉取密钥，用于从私有仓库拉取镜像

```shell
# 代码中固定使用这个key "image-pull-secret", 下属脚本给全部命名空间都创建了该密钥
# 定义变量
SECRET_NAME="image-pull-secret"
DOCKER_SERVER="your-private-registry.com"
DOCKER_USERNAME="yourusername"
DOCKER_PASSWORD="yourpassword"
DOCKER_EMAIL="youremail@example.com"

# 获取所有命名空间
NAMESPACE_LIST=$(kubectl get namespaces -o jsonpath="{.items[*].metadata.name}")

# 在每个命名空间中创建 Secret
for NAMESPACE in $NAMESPACE_LIST; do
echo "Creating secret '$SECRET_NAME' in namespace '$NAMESPACE'"
kubectl create secret docker-registry $SECRET_NAME \
--docker-server=$DOCKER_SERVER \
--docker-username=$DOCKER_USERNAME \
--docker-password=$DOCKER_PASSWORD \
--docker-email=$DOCKER_EMAIL \
--namespace=$NAMESPACE
done

```
