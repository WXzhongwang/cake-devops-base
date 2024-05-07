package com.rany.cake.devops.base.web.config;

import com.rany.cake.devops.base.web.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.resource.PathResourceResolver;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 拦截器配置类
 *
 * @author zhongshengwang
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Resource
    private AuthInterceptor authInterceptor;

    /**
     * 实现拦截器 要拦截的路径以及不拦截的路径
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");

        // 认证拦截器
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/api/devops/**")
                .order(10);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
        ;

        registry.addResourceHandler("/api/devops/**")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
