package com.rany.cake.devops.base.web.config;

import com.rany.cake.devops.base.web.interceptor.ExposeApiHeaderInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * spring mvc 配置
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/4/2 10:24
 */
@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private AuthInterceptor authInterceptor;
    @Resource
    private ExposeApiHeaderInterceptor exposeApiHeaderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
        // 认证拦截器
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/api/devops/**")
                .order(10);
        // 暴露服务请求头拦截器
        registry.addInterceptor(exposeApiHeaderInterceptor)
                .addPathPatterns("/cake/expose-api/**")
                .order(40);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/cake/expose-api/**")
                .allowedOrigins("*")
                // .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/api/devops/**")
//                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
//                .resourceChain(true)
//                .addResolver(new PathResourceResolver());
//    }


}
