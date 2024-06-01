# cake-devops-base

**cake-devops-base** JavaDevops实现。

1. SpringBoot 2.2.6.RELEASE
2. K8S 1.19.2
3. shell

## 依赖工具链

- [x] **sonarqube**: 质量检测https://www.sonarsource.com/products/sonarqube/downloads/
- [x] **sonar-scan-cli**: sonarqube提供的缺陷检测扫描工具
- [x] **linux shell**: shell
- [x] **GitLab**: 代码管理SCM https://docs.gitlab.cn/jh/api/api_resources.html
- [x] **Harbor**: 容器镜像仓库 https://goharbor.io/
- [x] **Docker**: 容器技术、虚拟化 https://www.docker.com/
- [x] **Docker Compose**: docker工具 https://github.com/docker/compose
- [x] **kubernetes**: 容器集群管理系统 http://docs.kubernetes.org.cn/
- [x] **AliYun ACR容器镜像服务**: 容器镜像服务(镜像加速中心)https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors
- [x] **websocket+rabbitmq**: 日志推送

## 远景

![ROBOT](https://github.com/WXzhongwang/cake-devops-base/blob/main/images/future.png)

## 能力规划

- [x] **主机管理**: 主机管理
- [x] **主机密钥管理**: 主机密钥管理
- [x] **代理管理**: 代理管理
- [x] **主机组管理**: 主机组管理
- [x] **脚本管理**: 脚本管理
- [x] **告警组管理**: 告警组管理
- [x] **WebHook管理**: WebHook管理
- [x] **主机告警配置**: 主机告警配置
- [x] **主机环境变量配置**: 主机环境变量配置
- [x] **脚本管理**: 脚本管理
- [x] **站内信管理**: 站内信管理

## 近期工作:

- [x] **React umi**: 搭建工程脚手架
- [x] **应用管理**: 应用列表页，新增应用
- [x] **应用管理**: 应用详情页
- [x] **应用管理**: 应用详情页环境列表，创建环境
- [x] **主机管理**: 主机列表，根据机组搜主机
- [x] **主机管理**: 新建主机
- [x] **主机管理**: 主机管理，左树（机组）右表（主机分页列表）
- [x] **应用管理**: 应用人员管理（分管不同的人员职能及角色），查询，更新，删除
- [x] **应用管理**: 部署页面更新，增加发布单
- [x] **基础能力**: pipeline增加进度监听器，使用观察者模式
- [x] **应用环境**: websocket+rabbitmq 采集日志并推送客户端实时展示发布进度
- [x] **主机监控**: 监控中心，监控主机状态，监控主机日志，监控主机性能，agent模式

## 待计划开始

- [ ] **主机管理**: 主机终端，主机运维
- [ ] **集群管理**: 集群列表
- [ ] **集群管理**: 集群终端、集群运维
- [ ] **应用环境**: 创建发布单，发布pipeline，发布历史

## 部分系统截图

![log_display.png](https://github.com/WXzhongwang/cake-devops-base/blob/main/images%2Flog_display.png)

![ROBOT](https://github.com/WXzhongwang/cake-devops-base/blob/main/images/WechatIMG869.jpeg)

![ROBOT](https://github.com/WXzhongwang/cake-devops-base/blob/main/images/web.jpg)

## 工程依赖

| 底层依赖              | 依赖类型       | 最新版本         | 必须 | 说明         | 地址                                               |
|:------------------|:-----------|:-------------|:---|:-----------|:-------------------------------------------------|
| uic-center        | 服务依赖       | 1.0-SNAPSHOT | 是  | 用户中心       | https://github.com/WXzhongwang/uic-center        | 
| cake-toolkit-all  | JAR 依赖     | 1.0.0        | 是  | toolkit工具包 | https://github.com/WXzhongwang/cake-toolkit      | 
| cake-dingtalk-sso | 服务依赖&jar依赖 | 1.0-SNAPSHOT | 是  | SSO        | https://github.com/WXzhongwang/cake-dingtalk-sso | 
| cake              | JAR 依赖     | 1.0-SNAPSHOT | 是  | DDD        | https://github.com/WXzhongwang/cake              | 

# 快速开始

[快速开始](https://github.com/WXzhongwang/cake-devops-base/blob/main/README_QUICKSTART.md)

## 欢迎共建

welcome to fork me !!!

## 联系我

![联系我](https://github.com/WXzhongwang/cake-devops-base/blob/main/images/CONCACT_ME.png)