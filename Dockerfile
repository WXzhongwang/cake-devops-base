# 定义构建参数 ENV
ARG ENV=dev

FROM openjdk:8-jdk-alpine
RUN apk add --no-cache bash

# 挂载目录
VOLUME /tmp

# 添加 appctl.sh 脚本
ADD appctl.sh /app/appctl.sh
RUN chmod +x /app/appctl.sh

ADD /start/target/cake-devops-service.jar /app/cake-devops-service.jar

# 设置环境变量
ENV JAVA_OPTS="-Xms512m -Xmx1024m \
                -Xloggc:/home/admin/gc.log \
                -XX:+PrintGCDetails \
                -XX:+PrintGCDateStamps \
                -XX:+PrintGCTimeStamps \
                -XX:+PrintGCCause \
                -XX:+PrintGCApplicationStoppedTime \
                -XX:+UseGCLogFileRotation \
                -XX:NumberOfGCLogFiles=10 \
                -XX:GCLogFileSize=100M"

# 设置 spring.profiles.active 环境变量
ENV SPRING_PROFILES_ACTIVE=${ENV}

# 设置 ENTRYPOINT
ENTRYPOINT ["/app/appctl.sh", "start"]
