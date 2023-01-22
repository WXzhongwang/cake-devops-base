# 尝试使用docker创建自定义基础镜像:

tomcat8:

```text

FROM centos:7
MAINTAINER zhongshengwang<18668485565@163.com>

ADD jdk-8u361-linux-x64.tar.gz /usr/local
ADD apache-tomcat-8.5.85.tar.gz /usr/local

RUN yum -y install vim

ENV MYPATH /home/admin

WORKDIR $MYPATH
ENV JAVA_HOME /usr/local/jdk1.8.0_361
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV CATALINA_HOME /usr/local/apache-tomcat-8.5.85
ENV CATALINE_BASH /usr/local/apache-tomcat-8.5.85
ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINE_HOME/bin

EXPOSE 8080

CMD /usr/local/apache-tomcat-8.5.85/bin/startup.sh && tail -f /usr/local/apache-tomcat-8.5.85/bin/logs/catalina.out
```

jdk8:

```text
FROM centos:7
  
RUN ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' > /etc/timezone

ADD jdk-8u361-linux-x64.tar.gz  /usr/local

ENV JAVA_HOME /usr/local/jdk1.8.0_221

ENV PATH ${PATH}:${JAVA_HOME}/bin

ENV CLASS_PATH=:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JAVA_HOME/jre/lib

```