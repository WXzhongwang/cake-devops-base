package com.rany.cake.devops.base.service.interceptor;


import com.rany.cake.devops.base.service.base.Constants;
import com.rany.cake.devops.base.service.utils.TraceIdUtil;
import com.rany.cake.toolkit.lang.time.Dates;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 * 拦截器，为所有请求添加一个traceId
 *
 * @author zhongshengwang
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    /**
     * 开始时间
     */
    private static final ThreadLocal<Date> START_HOLDER = ThreadLocal.withInitial(Date::new);

    /**
     * 请求序列
     */
    public static final ThreadLocal<String> SEQ_HOLDER = ThreadLocal.withInitial(TraceIdUtil::getTraceId);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果有上层调用就用上层的ID
        String traceId = request.getHeader(Constants.TRACE_ID);
        if (traceId == null) {
            traceId = TraceIdUtil.getTraceId();
        }

        MDC.put(Constants.TRACE_ID, traceId);
        log.info("api请求开始, requestId:" + SEQ_HOLDER.get());
        log.info("api请求开始时间:" + Dates.format(START_HOLDER.get(), Dates.YMD_HMSS));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 调用结束后删除
        Date endTime = new Date();
        MDC.remove(Constants.TRACE_ID);
        String sb = "api请求结束, requestId:" + SEQ_HOLDER.get() +
                ", api请求结束时间: " +
                Dates.format(endTime, Dates.YMD_HMSS) +
                ", api请求耗时:" +
                (endTime.getTime() - START_HOLDER.get().getTime()) +
                "ms";
        log.info(sb);
        SEQ_HOLDER.remove();
        START_HOLDER.remove();
    }
}
