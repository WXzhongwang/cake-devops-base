#jdk版本
FROM openjdk:8-jdk-alpine
RUN apk add --no-cache bash

#挂载目录
VOLUME /tmp

ADD /start/target/cake-devops-service.jar cake-devops-service.jar

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




#docker运行命令
ENTRYPOINT ["java", "${JAVA_OPTS}", "-Dspring.profiles.active=dev", "-jar", "/cake-devops-service.jar"]