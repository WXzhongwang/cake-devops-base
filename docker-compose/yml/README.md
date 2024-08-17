R```
docker build -f Dockerfile_mysql8.0 -t mysql:20240831 .
docker tag mysql:20240831 registry.cn-hangzhou.aliyuncs.com/cake-devops-base/mysql:20240831
docker push registry.cn-hangzhou.aliyuncs.com/cake-devops-base/mysql:20240831

```

```shell
# 本地brew安装的mysql停掉,听过代理方式链接k8s内mysql

brew services stop mysql@8.0
brew services stop mysql@8.0

kubectl port-forward svc/mysql 3306:3306 & \
kubectl port-forward svc/zookeeper 2181:2181 & \
kubectl port-forward svc/redis 6379:6379  & \
kubectl port-forward svc/rabbitmq 5672:5672
```