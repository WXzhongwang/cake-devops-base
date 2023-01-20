package com.rany.cake.devops.base.service.utils;

import com.rany.cake.devops.base.service.base.Constants;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 20:09
 * @email 18668485565163.com
 */
public final class KubernetesUtils {

    private KubernetesUtils() {

    }

    /**
     * businessUnit abbreviation english name
     *
     * @param businessUnit
     * @return
     */
    public String getNamespaceName(String businessUnit) {
        return Constants.DEFAULT_NAMESPACE_PREFIX + businessUnit;
    }

    public static String getReplicaAppName(String appName, String appEnvTag) {
        return appName + "-" + appEnvTag;
    }
}

