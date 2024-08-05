# 快速开始（本地二次开发模式）[过期]

## 0. 准备工作

1. zookeeper+dubbo 安装
2. jdk 安装
3. maven 安装
4. 数据库MySQL8.0.16安装（具体5.7以上都可以，最好是8.0）
5. idea 安装
6. nvm && pnpm 安装 建议使用nvm && pnpm管理前端依赖

## 1. 安装cake相关依赖

因为cake目前还未正式发布中心仓库，所以需要手动安装cake相关依赖。

```shell

选择自己本地的工程目录

mkdir -p ~/cake-workspace && cd cake-workspace

git clone https://github.com/WXzhongwang/cake

cd cake

mvn clean install -DskipTests=true 

```

## 2 安装cake-toolkit 相关依赖到本地

同上，因为cake-toolkit目前还未正式发布中心仓库，所以需要手动安装cake-toolkit相关依赖。

```shell

cd ~/cake-workspace

git clone https://github.com/WXzhongwang/cake-toolkit

cd cake-toolkit

mvn clean install -DskipTests=true 

```

## 3.启动UIC-CENTER用户中心服务

### 3.1 拉取代码&&安装依赖到本地

```shell

cd ~/cake-workspace

git clone https://github.com/WXzhongwang/uic-center

cd uic-center

mvn clean install -DskipTests=true 

```

> 这一步同时也会将uic-api这个二方包安装到本地仓库, 请确认uic-api是否安装成功

### 3.2 数据库还原

在代码根路径中包含一个UIC-CENTER服务所需数据库的dump文件，请通过数据库SQL文件还原数据库。

```text
比如：uic-center/sql/rany_uic_localhost-2024_01_17_20_30_32-dump.sql
```

### 3.3 本地启动

SprintBoot启动，端口默认为：6321

检查start/src/main/resources/application.yml配置文件，修改数据库配置信息, 默认spring.profiles.active=dev
> 启动类：com.rany.uic.UicApplication

## 3.启动 SSO-SERVER 统一登录

### 3.1 拉取代码&&安装依赖到本地

```shell

cd ~/cake-workspace

git clone https://github.com/WXzhongwang/cake-dingtalk-sso

cd cake-dingtalk-sso

mvn clean install -DskipTests=true 

```

> 这一步同时也会将cake-dingtalk-sso-starter这个二方包安装到本地仓库, 请确认cake-dingtalk-sso-starter是否安装成功

### 3.3 本地启动

SprintBoot启动，端口默认为：8765

检查cake-dingtalk-sso-server/src/main/resources/application.yml配置文件，部分钉钉配置信息需要替换
> 启动类：com.rany.cake.dingtalk.server.SsoServerApplication

## 4. 启动cake-devops-base

### 3.1 拉取代码&&安装依赖到本地

```shell

cd ~/cake-workspace

git clone https://github.com/WXzhongwang/cake-devops-base

cd cake-devops-base

mvn clean install -DskipTests=true 

```

### 3.2 数据库还原

在代码根路径中包含一个devops主工程服务所需数据库的dump文件，
请通过数据库SQL文件还原数据库。

```text
比如：cake-devops-base/sql/devops-2024_01_17_20_41_13-dump.sql
```

### 3.3 前端资源启动打包

```shell
nvm current ## 建议使用nvm && pnpm管理前端依赖
> v16.20.2

cd cake-devops-base/devops-frontend

pnpm install 

pnpm run build ##这异步会将前端资源打包到dist目录下
```

### 3.4 前端资源拷贝到后端工程resources目录下

源：cake-devops-base/devops-frontend/dist下全部资源
目标：cake-devops-base/start/src/main/resources/static下

```shell

cd cake-devops-front

cp -R  dist/* ../start/src/main/resources/static

```

>
因为工程目前是一个人独立开发，所以也不想整的太复杂，所以直接把前端资源拷贝到后端工程resources目录下了，有条件的同学或者对NGINX比较熟悉的同学，可以考虑将前端资源部署到Nginx上，然后通过Nginx进行反向代理进行负载均衡，这样会更安全一些。
> 但是注意一点，这个时候，com.rany.cake.devops.base.web.controller.IndexController中关于资源托管的部分，需要注释掉。

![前端资源代码.jpg](https://github.com/WXzhongwang/cake-devops-base/blob/main/images%2F%E5%89%8D%E7%AB%AF%E8%B5%84%E6%BA%90%E4%BB%A3%E7%A0%81.jpg)

### 3.5 启动本地服务

SprintBoot启动，端口默认为：8300

检查cake-devops-base/start/src/main/resources/application.yml配置文件，部分钉钉配置信息需要替换
> 启动类：com.rany.cake.devops.base.CakeDevopsBaseApplication
