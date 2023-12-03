package com.rany.cake.devops.base.service.utils;

import java.util.UUID;

/**
 * @author zhongshengwang
 */
public class TraceIdUtil {

    public static String getTraceId() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}
