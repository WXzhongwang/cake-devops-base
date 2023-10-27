# 本地开发踩坑

## DOCKER 安装

Docker是一种现代化的容器化技术，旨在提高应用程序的可移植性和可扩展性。Docker允许开发人员将应用程序以一种可重复的方式封装到容器中，然后将其部署到任何支持Docker的环境中。在本文中，我们将学习如何在Mac上安装Docker。
安装Docker 在Mac上安装Docker需要下载并安装Docker Desktop。Docker
Desktop是Mac和Windows上最流行的Docker环境之一，整合了开发和生产工作流，并提供了一个可视化的用户界面。
在安装Docker Desktop之前，必须确认Mac上的操作系统版本是否兼容Docker Desktop的最新版。

> 踩坑点：我的电脑是M1芯片，下载默认最新是4.4.1，后来发现启动docker desktop一直无法启动engine，一直提示引擎处于停止状态，后续降低版本发现能正常启动
> Docker Desktop 4.1.1 (69879) is currently the newest version available.

阿里云注册免费个人镜像，添加好重启docker，
其实这里可以考虑启动docker之前，就配置在/etc/docker/daemon.json文件内，没有直接创建一个，这样启动首次docker就可以自动带过来该配置了。

```json
{
  "builder": {
    "gc": {
      "defaultKeepStorage": "20GB",
      "enabled": true
    }
  },
  "experimental": false,
  // 添加如下配置
  "registry-mirrors": [
    "https://ozfe4klh.mirror.aliyuncs.com"
  ]
}
```

## portainer.io

docker容器启动成功后，还是老样子安装个人认为比较好的docker容器管理工具
这里我用的宿主机6000端口映射容器内9000端口

```shell
docker run -d -p 6000:9000 -v /var/run/docker.sock:/var/run/docker.sock --restart=always --name portainer portainer/portainer
```

## jekins

docker-compose.yml启动jenkins，docker-compose下载https://github.com/docker/compose

```yaml
  version: "3.1"
  services:
    jenkins:
      image: jenkins/jenkins
      container_name: jenkins
      ports:
        - 8080:8080
        - 50000:50000
      volumes:
        - /usr/local/app:/var/jenkins_home/
        - /var/run/docker.sock:/var/run/docker.sock
        - /usr/local/bin/docker:/usr/local/bin/docker
        - /etc/docker/daemon.json:/etc/docker/daemon.json
```

这里做个一个偷攻的做法，就是事先我本地就下载好了后续构建所需的一些工具，我将其放置解压在了 /usr/local/app路径下，包括

1. apache maven
2. jdk8等一系列工具
3. 包括宿主机docker的映射
   通过数据卷的方式，这样方便在后续jenkins使用工具链时很好的帮助在容器内配置对应的工具全局路径

```shell
docker-compose up -d # 启动jenkins，初次启动比较慢
docker exec -it jenkins cat /var/jenkins_home/secrets/initialAdminPassword
```