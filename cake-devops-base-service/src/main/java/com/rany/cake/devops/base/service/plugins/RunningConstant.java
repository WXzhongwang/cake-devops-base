package com.rany.cake.devops.base.service.plugins;

public class RunningConstant {
    public static final String WORKSPACE_HOME = "WORKSPACE_HOME";

    /**
     * 通过调用curl接口判断封网情况
     */
    public static final String FORBIDDEN_CHECK_ADDRESS = "FORBIDDEN_CHECK_ADDRESS";
    public static final String FORBIDDEN_CURL_ADDRESS = "http://localhost:7100/approval/forbidden/check?appId=%s";
    public static final String FORBIDDEN_CHECK_REQUIRED = "FORBIDDEN_CHECK_REQUIRED";


    public static final String DEFAULT_SHELL_CMD = "mvn clean package -U -Dmaven.compile.fork=true -P${ENV}";
    /**
     * SONAR
     */
    public static final String SONAR_ADDRESS_URL = "SONAR_ADDRESS_URL";

    public static final String SONAR_LOGIN = "SONAR_LOGIN";
    public static final String SONAR_PWD = "SONAR_PWD";

    public static final String DING_WEBHOOK_URL = "DING_WEBHOOK_URL";
    public static final String CODE_REPO_URL = "REPO_URL";
    public static final String CODE_BRANCH_NAME = "BRANCH_NAME";
}
