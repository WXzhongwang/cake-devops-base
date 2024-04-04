package com.rany.cake.devops.base.web.annotation;


import com.rany.cake.devops.base.api.common.base.BaseCommand;
import com.rany.cake.dingtalk.sdk.beans.SsoUser;
import com.rany.cake.dingtalk.sdk.utils.SsoUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect
@Component
public class UserInjectionAspect {
    @Before("execution(* com.rany.cake.devops.base.web.controller.*Controller.*(..))")
    public void injectUserIntoBaseCommand(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Object[] args = joinPoint.getArgs();
        SsoUser currentUser = SsoUtil.getCurrentUser(request);
        for (Object arg : args) {
            if (arg instanceof BaseCommand) {
                if (currentUser != null) {
                    ((BaseCommand) arg).setUser(currentUser.getUserId());
                }
            }
        }
    }
}
