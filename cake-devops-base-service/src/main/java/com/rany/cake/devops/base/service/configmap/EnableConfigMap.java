package com.rany.cake.devops.base.service.configmap;

import java.lang.annotation.*;

/**
 * EnableConfigMap
 *
 * @author zhongwang
 * @date 2024/7/30
 * @slogan Why Not
 * @email zhongshengwang.zsw@alibaba-inc.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
// @Import(ConfigMapAutoRegistar.class)
public @interface EnableConfigMap {
    String appName();

    String namespace();
}