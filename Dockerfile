#jdk版本
FROM openjdk:8-jdk-alpine

#挂载目录
VOLUME /tmp

ADD /start/target/cake-devops-service.jar cake-devops-service.jar

#docker运行命令
ENTRYPOINT ["java","-Dspring.profiles.active=test","-jar","/cake-devops-service.jar"]