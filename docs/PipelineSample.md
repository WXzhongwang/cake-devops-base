```groovy
pipeline {

    agent any
    environment {
        harborRepo = 'heianapp'
        harborUser = 'heian99'
        harborPasswd = 'NG+.mK4M-(s4CYX'
    }
    // 存放所有任务的合集
    stages {
        stage('拉取Git代码') {
            steps {
                echo '拉取Git代码'
                checkout([$class: 'GitSCM', branches: [[name: '${tag}']], extensions: [], userRemoteConfigs: [[url: 'https://gitee.com/chengfeng99/java-demo.git']]])
            }
        }

        stage('检测代码质量') {
            steps {
                echo '检测代码质量'
                sh '/var/jenkins_home/sonar-scanner/bin/sonar-scanner -Dsonar.sources=./ -Dsonar.projectname=${JOB_NAME} -Dsonar.projectKey=${JOB_NAME} -Dsonar.java.binaries=target/ -Dsonar.login=19d0d6b885e18455d257d61da08776bd4e180c04'
            }
        }

        stage('构建代码') {
            steps {
                echo '构建代码'
                sh '/var/jenkins_home/maven/bin/mvn clean package -DskipTests'
            }
        }

        stage('制作自定义镜像并发布Harbor') {
            steps {
                echo '制作自定义镜像并发布Harbor'
                sh '''
                cp ./target/*.jar ./docker/demo.jar
                cd ./docker
                docker build -t ${JOB_NAME}:${BUILD_NUMBER} . '''

                sh '''docker login -u ${harborUser} -p ${harborPasswd} 
                docker tag ${JOB_NAME}:${BUILD_NUMBER} ${harborUser}/${harborRepo}:${JOB_NAME}_${BUILD_NUMBER}
                docker push ${harborUser}/${harborRepo}:${JOB_NAME}_${BUILD_NUMBER}'''
            }
        }

        stage('基于Harbor部署工程') {
            steps {
                echo '基于Harbor部署工程'
                sshPublisher(publishers: [sshPublisherDesc(configName: 'node-Linux32', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '''cd /opt/java/
echo "测试成功" >> log.txt
 date >> log.txt''', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: 'target/*.jar,docker/*')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }
    }
}
```