package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.entity.UserEventLog;
import com.rany.cake.devops.base.domain.repository.param.UserEventLogQueryPageParam;

/**
 * 用户事件日志
 *
 * @author zhongshengwang
 */
public interface UserEventLogRepository {
    UserEventLog find(Long id);

    void save(UserEventLog log);

    int update(UserEventLog log);

    Page<UserEventLog> pageQuery(UserEventLogQueryPageParam param);
}
