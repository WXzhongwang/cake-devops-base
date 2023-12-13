//package com.rany.cake.devops.base.service.jekins;
//
//
//import com.offbytwo.jenkins.JenkinsServer;
//import com.offbytwo.jenkins.client.JenkinsHttpClient;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//
///**
// * 连接 Jenkins
// *
// * @author zhongshengwang
// * @description TODO
// * @date 2023/1/17 19:17
// * @email 18668485565163.com
// */
//public class JenkinsConnect {
//
//    private JenkinsConnect() {
//    }
//
//    /**
//     * 连接 Jenkins 需要设置的信息
//     */
//    static final String JENKINS_URL = "http://127.0.0.1:9999/";
//    /**
//     * 推荐使用token
//     */
//    static final String JENKINS_TOKEN = "11bc412225b0d4d677cb6a29f19e751e3a";
//    static final String JENKINS_USERNAME = "admin";
//    static final String JENKINS_PASSWORD = "admin";
//
//    /**
//     * Http 客户端工具
//     * <p>
//     * 如果有些 API 该Jar工具包未提供，可以用此Http客户端操作远程接口，执行命令
//     *
//     * @return
//     */
//    public static JenkinsHttpClient getClient() {
//        JenkinsHttpClient jenkinsHttpClient = null;
//        try {
//            jenkinsHttpClient = new JenkinsHttpClient(new URI(JENKINS_URL), JENKINS_USERNAME, JENKINS_TOKEN);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        return jenkinsHttpClient;
//    }
//
//    /**
//     * 连接 Jenkins
//     */
//    public static JenkinsServer connection() {
//        JenkinsServer jenkinsServer = null;
//        try {
//            jenkinsServer = new JenkinsServer(new URI(JENKINS_URL), JENKINS_USERNAME, JENKINS_TOKEN);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        return jenkinsServer;
//    }
//}
