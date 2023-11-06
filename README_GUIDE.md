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

![UIC](https://github.com/WXzhongwang/cake-devops-base/blob/main/images/portainer.png)

## jekins

```shell
docker pull jenkins/jenkins:2.319.1-lts
```

docker-compose.yml启动jenkins，docker-compose下载https://github.com/docker/compose

```yaml
  version: "3.1"
  services:
    jenkins:
      image: jenkins/jenkins:2.319.1-lts
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

这里做个一个需要关注的做法，就是事先我本地就下载好了后续构建所需的一些工具，我将其放置解压在了 /usr/local/app路径下，包括

1. apache maven
2. jdk8等一系列工具
3. 包括宿主机docker的映射
   通过数据卷的方式，这样方便在后续jenkins使用工具链时很好的帮助在容器内配置对应的工具全局路径

```shell
docker-compose up -d # 启动jenkins，初次启动比较慢
docker exec -it jenkins cat /var/jenkins_home/secrets/initialAdminPassword
```

## SonarQube

docker-compose.yml启动sonarqube,依赖postgres数据库，所以这里可以通过stack的方式启动两个容器

```yaml
version: "3.1"
services:
  db:
    image: postgres
    container_name: db
    ports:
      - 5432:5432
    networks:
      - sonarnet
    environment:
      POSTGRES_USER: sonar
      POSTGRES_PASSWORD: sonar
  sonarqube:
    image: sonarqube
    container_name: sonarqube
    depends_on:
      - db
    ports:
      - "6000:9000"
    networks:
      - sonarnet
    environment:
      SONAR_JDBC_URL: jdbc:postgresql://db:5432/sonar
      SONAR_JDBC_USERNAME: sonar
      SONAR_JDBC_PASSWORD: sonar
networks:
  sonarnet:
    driver: bridge
```

配置本地环境，因为使用的是M1，sonar镜像支持M1，最低版本已经是10了，所以对java8已经放弃支持了。

```shell
export MAVEN_HOME=/usr/local/src/apache-maven-3.6.3
export JAVA_11_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home
export JAVA_8_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home
#默认JDK11
export JAVA_HOME=$JAVA_8_HOME
#alias命令动态切换JDK版本
alias jdk11="export JAVA_HOME=$JAVA_11_HOME"
alias jdk8="export JAVA_HOME=$JAVA_8_HOME"

export PATH=$MAVEN_HOME/bin:$JAVA_HOME/bin:$PATH
```

但是不影响使用，可以考虑通过本地IDEA的maven，关联JDK11来进行sonar扫描，后续jenkins自动化可以使用。
![本地开启扫描方式](https://github.com/WXzhongwang/cake-devops-base/blob/main/images/WX20231027-223736@2x.png)
运行结果截图：
![sonar运行结果截图](https://github.com/WXzhongwang/cake-devops-base/blob/main/images/sonar.png)

# DUBBO-ADMIN && Zookeeper

启动zk&&dubbo-admin用于服务测试

```shell
docker run -itd --name zookeeper --network zk -p 2181:2181 -p 2888:2888 -p 3888:3888 zookeeper
docker run -d --name dubbo-admin --network zk -p 8088:8080 -e admin.registry.address=zookeeper://172.18.0.1:2181 -e admin.config-center=zookeeper://172.18.0.1:2181 -e admin.metadata-report.address=zookeeper://172.18.0.1:2181 apache/dubbo-admin
```

## Shenyu

```shell

docker network create shenyu
docker run --name shenyu-admin \
-v /Users/yuanjinxiu/shenyu-v2.6.0/shenyu-admin/ext-lib:/opt/shenyu-admin/ext-lib \
-v /Users/yuanjinxiu/shenyu-v2.6.0/shenyu-admin/logs:/opt/shenyu-admin/logs \
-e "SPRING_PROFILES_ACTIVE=mysql"\
 -e "spring.datasource.url=jdbc:mysql://host.docker.internal:3306/shenyu?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull" \
 -e "spring.datasource.username=root" \
 -e "spring.datasource.password=xxx" \
 -d -p 9095:9095 \
 --net shenyu \
 apache/shenyu-admin:2.6.0



docker run -d \
  -p 9195:9195 \
  --name shenyu-bootstrap \
  -v /Users/yuanjinxiu/shenyu-v2.6.0/shenyu-bootstrap/logs/:/opt/shenyu-bootstrap/logs \
  -v /Users/yuanjinxiu/shenyu-v2.6.0/shenyu-bootstrap/conf/:/opt/shenyu-bootstrap/conf \
  --net shenyu \
  --env SHENYU_SYNC_WEBSOCKET_URLS=ws://shenyu-admin:9095/websocket \
  apache/shenyu-bootstrap:2.6.0
```

