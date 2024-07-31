# 定义构建参数 ENV
ARG ENV=dev
# 设置 spring.profiles.active 环境变量
ENV SPRING_PROFILES_ACTIVE=${ENV}

FROM openjdk:8-jdk-alpine
RUN apk add --no-cache bash

# 挂载目录
VOLUME /tmp

# 添加 appctl.sh 脚本
ADD appctl.sh /app/appctl.sh
RUN chmod +x /app/appctl.sh

ADD /start/target/cake-devops-service.jar /app/cake-devops-service.jar

# 设置 ENTRYPOINT
ENTRYPOINT ["/app/appctl.sh", "start"]
