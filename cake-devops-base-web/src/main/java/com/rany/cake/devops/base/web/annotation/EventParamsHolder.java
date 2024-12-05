package com.rany.cake.devops.base.web.annotation;

import com.rany.cake.devops.base.service.base.UserHolder;
import com.rany.cake.devops.base.service.utils.Servlets;
import com.rany.cake.devops.base.util.EventKeys;
import com.rany.cake.devops.base.web.config.LogInterceptor;
import com.rany.cake.dingtalk.sdk.beans.SsoUser;
import com.rany.cake.toolkit.lang.collect.MutableMap;
import com.rany.cake.toolkit.lang.reflect.BeanMap;
import com.rany.cake.toolkit.lang.time.Dates;
import com.rany.cake.toolkit.lang.utils.Maps;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

/**
 * 操作日志参数信息
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/1/23 17:24
 */
public class EventParamsHolder {

    /**
     * 参数
     */
    private static final ThreadLocal<MutableMap<String, Object>> PARAMS = ThreadLocal.withInitial(Maps::newMutableLinkedMap);

    public static MutableMap<String, Object> get() {
        return PARAMS.get();
    }

    public static void set(MutableMap<String, Object> user) {
        PARAMS.set(user);
    }

    public static void remove() {
        PARAMS.remove();
    }

    /**
     * 设置参数
     *
     * @param key   key
     * @param value value
     */
    public static void addParam(String key, Object value) {
        PARAMS.get().put(key, value);
    }

    /**
     * 设置参数
     *
     * @param value value
     */
    public static void addParams(Object value) {
        if (value == null) {
            return;
        }
        PARAMS.get().putAll(BeanMap.create(value));
    }

    /**
     * 设置是否保存
     *
     * @param save 是否保存
     */
    public static void setSave(boolean save) {
        PARAMS.get().put(EventKeys.INNER_SAVE, save);
    }

    /**
     * 设置默认参数
     */
    public static void setDefaultEventParams() {
        // 请求时间
        EventParamsHolder.addParam(EventKeys.INNER_REQUEST_TIME, Dates.current());
        // 登陆接口为空
        SsoUser ssoUser = UserHolder.get();
        if (ssoUser != null) {
            EventParamsHolder.addParam(EventKeys.INNER_USER_ID, ssoUser.getUserId());
            EventParamsHolder.addParam(EventKeys.INNER_USER_NAME, ssoUser.getUserName());
        }
        // 请求序列
        EventParamsHolder.addParam(EventKeys.INNER_REQUEST_SEQ, LogInterceptor.SEQ_HOLDER.get());
        // 请求信息
        Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(s -> (ServletRequestAttributes) s)
                .map(ServletRequestAttributes::getRequest)
                .ifPresent(request -> {
                    EventParamsHolder.addParam(EventKeys.INNER_REQUEST_USER_AGENT, Servlets.getUserAgent(request));
                    EventParamsHolder.addParam(EventKeys.INNER_REQUEST_IP, Servlets.getRemoteAddr(request));
                });
    }

}
