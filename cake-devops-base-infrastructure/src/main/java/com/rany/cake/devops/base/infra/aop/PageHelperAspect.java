package com.rany.cake.devops.base.infra.aop;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.rany.cake.devops.base.domain.repository.param.BasePageParam;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * 分页切面处理
 */
@Aspect
@Component
@Slf4j
public class PageHelperAspect {

    @Before("@annotation(pagingQuery)")
    public void pagingQuery(JoinPoint joinPoint, PagingQuery pagingQuery) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String name = method.getName();
        log.info("方法进入分页aop，方法名称为: {}，参数: {}", name, JSON.toJSONString(args));
        if (null == args || args.length == 0) {
            log.info("{} 方法无参数", name);
            return;
        }
        Object arg = args[0];

        if (!(arg instanceof BasePageParam)) {
            log.info("{} 分页方法参数必须是BasePageParam的子类", name);
            return;
        }
        Integer pageNumber = null;
        Integer pageSize = null;
        BasePageParam param = (BasePageParam) arg;
        pageNumber = param.getPageNo();
        pageSize = param.getPageSize();
        try {
            log.info("PageHelper分页参数，pageNum: {}，pageSize: {}", pageNumber, pageSize);
            if (pageNumber != null && pageNumber > 0 && pageSize != null && pageSize > 0) {
                PageHelper.startPage(pageNumber, pageSize, true);
            }
        } catch (Throwable e) {
            log.error("PageHelper分页error", e);
            PageHelper.clearPage();
        }
    }
}