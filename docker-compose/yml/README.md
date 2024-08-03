```
docker build -f Dockerfile_mysql8.0 -t mysql:20240831 .
docker tag mysql:20240831 registry.cn-hangzhou.aliyuncs.com/cake-devops-base/mysql:20240831
docker push registry.cn-hangzhou.aliyuncs.com/cake-devops-base/mysql:20240831
```