```
docker build -f Dockerfile_mysql8.0 -t mysql:20240831 .
docker tag mysql:20240831 registry.cn-hangzhou.aliyuncs.com/cake-devops-base/mysql:20240831
docker push registry.cn-hangzhou.aliyuncs.com/cake-devops-base/mysql:20240831
```


```shell
kubectl port-forward svc/mysql 3306:3306 代理端口3306

# 本地brew安装的mysql停掉,听过代理方式链接k8s内mysql

brew services stop mysql@8.0
brew services stop mysql@8.0
```