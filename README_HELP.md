# 工程简介

# 延伸阅读

CPU以核心为单位，常用毫核做限制。
1核心=100%=1000m

内存以字节为单位，常用Mi做限制
1Ti=1024Gi=1024*1024Mi

maven+jacoco+sonarqube进行代码静态扫描

harbor-java-client:
https://github.com/0xl2oot/harbor-java-client


 <!--jenkins-java-client-->

        <dependency>
            <groupId>com.offbytwo.jenkins</groupId>
            <artifactId>jenkins-client</artifactId>
            <version>0.3.8</version>
        </dependency>

```
docker run -d -p 9999:8080 -p 50000:50000 --name jenkins --env JAVA_OPTS=-Dhudson.model.DownloadService.noSignatureCheck=true \
--privileged=true -v /Users/tutu/jenkins_home:/var/jenkins_home jenkins/jenkins
```

```
docker run -it -d --name=socat -p 2375:2375 -v    /var/run/docker.sock:/var/run/docker.sock bobrik/socat TCP4-LISTEN:2375,fork,reuseaddr UNIX-CONNECT:/var/run/docker.sock
```

```text
2、JaCoCo(使用最多)
DevOps流水线上用的 SonarQube就是用的这个插件。idea自带插件，计算单元测试覆盖率 开启方式：idea 使用JaCoCo插件测试覆盖率并展示Branch,%(分支覆盖率) jacoco插件
```

```
注意挂载-v /var/run/docker.sock:/var/run/docker.sock -v $(which docker):/usr/bin/docker，才可以共享宿主机的docker资源
```

Jenkins slave agent 