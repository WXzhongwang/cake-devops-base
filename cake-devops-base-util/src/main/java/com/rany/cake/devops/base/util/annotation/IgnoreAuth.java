package com.rany.cake.devops.base.util.annotation;

import java.lang.annotation.*;

/**
 * 无需认证注解
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/4/2 10:36
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {
}
