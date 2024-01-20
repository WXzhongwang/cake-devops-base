package com.rany.cake.devops.base.service.plugins.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 插件名称注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PluginName {

    String value();

    /**
     * 是否前端展示
     *
     * @return 默认展示
     */
    boolean display() default true;
}
