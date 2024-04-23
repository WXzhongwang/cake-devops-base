package com.rany.cake.devops.base.web.config;

import com.rany.cake.devops.base.service.base.UserHolder;
import com.rany.cake.devops.base.service.utils.Servlets;
import com.rany.cake.devops.base.util.ResultCode;
import com.rany.cake.devops.base.util.annotation.IgnoreAuth;
import com.rany.cake.dingtalk.sdk.beans.SsoUser;
import com.rany.cake.dingtalk.sdk.utils.SsoUtil;
import com.rany.cake.toolkit.lang.constant.StandardContentType;
import com.rany.cake.toolkit.lang.wrapper.HttpWrapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 是否跳过
        final boolean ignore = ((HandlerMethod) handler).hasMethodAnnotation(IgnoreAuth.class);
        SsoUser currentUser = SsoUtil.getCurrentUser(request);
        HttpWrapper<?> rejectWrapper = null;
        UserHolder.set(currentUser);
        if (currentUser == null) {
            rejectWrapper = HttpWrapper.of(ResultCode.UNAUTHORIZED);
        }
        // 驳回接口设置返回
        if (rejectWrapper != null) {
            response.setContentType(StandardContentType.APPLICATION_JSON);
            Servlets.transfer(response, rejectWrapper.toJsonString().getBytes());
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request,
                                @NotNull HttpServletResponse response,
                                @NotNull Object handler, Exception ex) {
        UserHolder.remove();
    }
}
