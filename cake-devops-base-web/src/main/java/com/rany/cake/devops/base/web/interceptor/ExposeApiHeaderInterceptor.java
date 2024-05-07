package com.rany.cake.devops.base.web.interceptor;

import com.rany.cake.devops.base.service.utils.Servlets;
import com.rany.cake.devops.base.util.ResultCode;
import com.rany.cake.devops.base.util.annotation.IgnoreCheck;
import com.rany.cake.toolkit.lang.constant.StandardContentType;
import com.rany.cake.toolkit.lang.wrapper.HttpWrapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 暴露api请求头拦截器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/29 16:09
 */
@Component
public class ExposeApiHeaderInterceptor implements HandlerInterceptor {

    @Value("${expose.api.access.header}")
    private String accessHeader;

    @Value("${expose.api.access.secret}")
    private String accessSecret;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws IOException {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 是否跳过
        final boolean ignore = ((HandlerMethod) handler).hasMethodAnnotation(IgnoreCheck.class);
        if (ignore) {
            return true;
        }
        final boolean access = accessSecret.equals(request.getHeader(accessHeader));
        if (!access) {
            response.setContentType(StandardContentType.APPLICATION_JSON);
            Servlets.transfer(response, HttpWrapper.of(ResultCode.ILLEGAL_ACCESS).toJsonString().getBytes());
        }
        return access;
    }

}
