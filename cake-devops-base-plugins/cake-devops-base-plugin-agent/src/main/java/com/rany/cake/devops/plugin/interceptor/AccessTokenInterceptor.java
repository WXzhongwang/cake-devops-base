package com.rany.cake.devops.plugin.interceptor;

import com.rany.cake.devops.plugin.common.constant.ResultCode;
import com.rany.cake.devops.plugin.utils.Servlets;
import com.rany.cake.toolkit.lang.constant.StandardContentType;
import com.rany.cake.toolkit.lang.wrapper.HttpWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问拦截器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/6/29 17:02
 */
@Component
public class AccessTokenInterceptor implements HandlerInterceptor {

    @Value("${agent.access.header}")
    private String accessHeader;

    @Value("${agent.access.secret}")
    private String accessSecret;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String accessKeyHeader = request.getHeader(accessHeader);
        if (accessSecret.equals(accessKeyHeader)) {
            return true;
        }
        // 非法访问
        response.setContentType(StandardContentType.APPLICATION_JSON);
        Servlets.transfer(response, HttpWrapper.of(ResultCode.INVALID_ACCESS_TOKEN).toJsonString().getBytes());
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

}
