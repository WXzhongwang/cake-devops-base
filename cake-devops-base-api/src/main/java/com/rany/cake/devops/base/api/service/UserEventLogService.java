package com.rany.cake.devops.base.api.service;


/**
 * 用户操作日志服务
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/1/22 20:19
 */
public interface UserEventLogService {

    /**
     * 记录日志
     *
     * @param eventType 操作
     * @param isSuccess 是否成功
     */
    void recordLog(Integer eventType, boolean isSuccess);

}
