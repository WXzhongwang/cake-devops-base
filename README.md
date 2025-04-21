# cake-devops-base

## 📚简介

🔥cake-devops-base是一款基于JAVA开发的CICD应用，使用简单，完全免费，代码开源。

<p align="center">
    <img alt="GitHub Repo stars" src="https://img.shields.io/github/stars/WXzhongwang/cake-devops-base?style=social">
    <img alt="GitHub forks" src="https://img.shields.io/github/forks/WXzhongwang/cake-devops-base?style=social">
    <img alt="GitHub license" src="https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg">
</p>
-------------------------------------------------------------------------------


**cake-devops-base** Java Devops实现，技术栈:

1. SpringBoot
2. RabbitMQ
3. Redis
4. SLS
5. K8S 1.19.2
6. shell
7. 前端采用React

## 特性

- [x] **代码托管**: 支持多种代码源托管，支持二开拓展
    - Gitlab
    - Github
    - CodeUp（阿里云云效）
- [x] **镜像仓库**:
    - Harbor容器镜像仓库 https://goharbor.io/
    - 阿里云容器服务（ACS）容器镜像服务 https://www.aliyun.com/product/acr
- [x] **Docker on kubernetes**: 容器技术、虚拟化 https://www.docker.com/, 容器集群管理系统 http://docs.kubernetes.org.cn/
- [x] **sonarqube&sonar-scan-cli**: sonarqube提供的缺陷检测扫描工具,
  质量检测https://www.sonarsource.com/products/sonarqube/downloads/
- [x] **websocket+rabbitmq+SLS**: 日志推送，实时查看部署进度，支持查看历史部署情况
- [x] **应用成员**: 应用成员管理，可查看应用人员，不同角色不同职责
- [x] **发布管控**: 发布审批、发布管控
- [x] **多环境部署**: 一套代码多个环境部署

## 技术层面

- [x] **dingtalk**: 基于钉钉扫码登录实现的sso
- [x] **OSS**: 基于cms-client实现的前端代码推送和部署（灰度&资源托管&CDN加速）
- [x] **SLS**: pipeline构建日志写入SLS便于回溯，查询
- [x] **cake-cms**: CDN+前端资源统一管理（多版本发布，可回滚，可灰度）
- [x] **sheep**: 自己开发的前端资源推送CDN工具

## 远景

![ROBOT](https://github.com/WXzhongwang/cake-devops-base/blob/main/images/future.png)

## 产品菜单

- [ ] **工作台**: 工作台展示
- [x] **应用中心**: 应用中心，包括应用创建、应用详情、应用成员管理、应用发布单、应用部署、应用发布审批、应用发布历史查看，整个CI\CD环节全部能力
- [x] **运维管理**:
    - [x] **集群管理**: 管理维护多个K8S集群
    - [x] **主机管理**: 主机管理，主机终端，主机运维
    - [x] **主机密钥管理**: 密钥列表，密钥创建，密钥更新，密钥删除，密钥查看
    - [x] **主机代理管理**: 代理列表，代理创建，代理更新，代理删除，代理查看
    - [x] **主机监控**: 主机监控，监控主机状态，监控主机日志，监控主机性能，agent安装，主机环境变量、SFTP管控、主机告警配置、主机告警历史
    - [ ] **批量执行**: 批量执行，批量脚本执行
    - [ ] **执行历史**: 批量执行历史
    - [ ] **主机任务调度**: 支持主机任务、定时调度执行脚本
- [x] **系统管理**:
    - [x] **webhook管理**: webhook创建，webhook更新，webhook删除，webhook查看
    - [x] **告警组**: 告警组创建，告警组更新，告警组删除，告警组查看，告警组成员管理
    - [x] **脚本管理**: 脚本创建，脚本更新，脚本删除，脚本查看，脚本列表
    - [x] **系统日志**: 系统审计日志查看、查询
    - [ ] **全局系统配置**: 系统配置创建，系统配置更新
    - [ ] **应用监控**: 服务指标监控，包括线程池、磁盘空间等

## _待计划开始

- [ ] **前端主框架优化**: 前端主框架优化重构（loading， ts检查等）
- [ ] **按钮操作权限**: 按钮操作权限
- [ ] **脚本调度**: 调度管理
- [ ] **站内信**: 站内信
- [ ] **全局系统配置**: 全局系统配置
- [ ] **工作台**: 工作台展示
- [ ] **分批、回滚发布**: 分批发布、回滚发布
- [ ] **应用监控**: 应用监控
- [ ] **K8S POD运维**: K8S POD运维

## 部分系统截图

![log_display.png](https://github.com/WXzhongwang/cake-devops-base/blob/main/images%2Flog_display.png)

![ROBOT](https://github.com/WXzhongwang/cake-devops-base/blob/main/images/WechatIMG869.jpeg)

![ROBOT](https://github.com/WXzhongwang/cake-devops-base/blob/main/images/web.jpg)

![ROBOT](https://github.com/WXzhongwang/cake-devops-base/blob/main/images/主机监控.png)

## 工程依赖

| 底层依赖              | 依赖类型       | 最新版本         | 必须 | 说明         | 地址                                               |
|:------------------|:-----------|:-------------|:---|:-----------|:-------------------------------------------------|
| cake-ops          | 服务依赖       | 1.0-SNAPSHOT | 是  | 用户中心，角色中心  | https://github.com/WXzhongwang/cake-ops          | 
| cake-toolkit-all  | JAR 依赖     | 1.0.0        | 是  | toolkit工具包 | https://github.com/WXzhongwang/cake-toolkit      | 
| cake-dingtalk-sso | 服务依赖&jar依赖 | 1.0-SNAPSHOT | 是  | SSO        | https://github.com/WXzhongwang/cake-dingtalk-sso | 
| cake              | JAR 依赖     | 1.0-SNAPSHOT | 是  | DDD        | https://github.com/WXzhongwang/cake              | 

# 快速开始

[快速开始](https://github.com/WXzhongwang/cake-devops-base/blob/main/README_QUICKSTART.md)

## 欢迎共建

welcome to fork me !!!

## 联系我

![联系我](https://github.com/WXzhongwang/cake-devops-base/blob/main/images/CONCACT_ME.png)