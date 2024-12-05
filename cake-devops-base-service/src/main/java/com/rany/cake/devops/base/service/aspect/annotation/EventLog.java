package com.rany.cake.devops.base.service.aspect.annotation;

import com.rany.cake.devops.base.api.common.enums.EventType;

import java.lang.annotation.*;

/**
 * 用户事件注解
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/1/22 17:50
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventLog {

    /**
     * @return 事件类型
     */
    EventType value();

}
