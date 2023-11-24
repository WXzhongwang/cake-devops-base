package com.rany.cake.devops.base.service.plugins;

public class RunningConstant {

    /**
     * 打包机器IP
     */
    public static final String BUILDER_IP = "BUILDER_IP";
    public static final String BUILDER_PORT = "BUILDER_PORT";
    public static final String BUILDER_REMOTE_USER = "BUILDER_REMOTE_USER";
    public static final String BUILDER_REMOTE_PWD = "BUILDER_REMOTE_PASSWORD";
    /**
     * 通过调用curl接口判断实际的审批情况
     */
    public static final String APPROVAL_CHECK_ADDRESS = "APPROVAL_CHECK_ADDRESS";
    public static final String APPROVAL_CURL_ADDRESS = "http://localhost:7100/approval/approval/check?approvalNumber=%s";
    public static final String APPROVAL_CHECK_REQUIRED = "APPROVAL_CHECK_REQUIRED";
    /**
     * 通过调用curl接口判断封网情况
     */
    public static final String FORBIDDEN_CHECK_ADDRESS = "FORBIDDEN_CHECK_ADDRESS";
    public static final String FORBIDDEN_CURL_ADDRESS = "http://localhost:7100/approval/forbidden/check?appId=%d";
    public static final String FORBIDDEN_CHECK_REQUIRED = "FORBIDDEN_CHECK_REQUIRED";


    public static final String DEFAULT_SHELL_CMD = "mvn clean package -U -Dmaven.compile.fork=true -P${ENV}";
    /**
     * SONAR
     */
    public static final String SONAR_ADDRESS_URL = "SONAR_ADDRESS_URL";

    public static final String SONAR_LOGIN = "SONAR_LOGIN";
    public static final String SONAR_PWD = "SONAR_PWD";

    public static final String DING_WEBHOOK_URL = "DING_WEBHOOK_URL";
}
