package com.rany.cake.devops.base.service.cloud;

/**
 * KubernetesConstants
 *
 * @author zhongshengwang
 */
public final class KubernetesConstants {
    private KubernetesConstants() {
    }

    public static final Integer DEFAULT_SERVICE_PORT = 80;
    public static final String DEFAULT_SERVICE_RESOURCE_VERSION = "v1";
    public static final String DEFAULT_SERVICE_SUFFIX = "-svc";
    public static final String DEFAULT_BACKEND_SERVICE = "default-backend";
    public static final Integer DEFAULT_BACKEND_SERVICE_PORT = 80;
    public static final Integer DEFAULT_WEB_SERVICE_PORT = 8300;
}
