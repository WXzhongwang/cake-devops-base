package com.rany.cake.devops.base.api.service;


import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.common.enums.EventType;
import com.rany.cake.devops.base.api.dto.log.UserEventLogDTO;
import com.rany.cake.devops.base.api.query.log.UserEventLogPageQuery;

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
    void recordLog(EventType eventType, boolean isSuccess);


    Page<UserEventLogDTO> pageQueryLog(UserEventLogPageQuery query);
}
