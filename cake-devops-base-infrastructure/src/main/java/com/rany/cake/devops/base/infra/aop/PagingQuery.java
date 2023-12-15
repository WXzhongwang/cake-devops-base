package com.rany.cake.devops.base.infra.aop;

import java.lang.annotation.*;


@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PagingQuery {
}
