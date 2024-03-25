package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.dto.HostAlarmHistoryDTO;
import com.rany.cake.devops.base.api.query.HostAlarmHistoryPageQuery;

/**
 * 告警服务
 *
 * @author zhongshengwang
 * @description 告警服务
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface HostAlarmService {

    /**
     * 分页查询告警历史
     *
     * @param hostAlarmHistoryPageQuery 查询告警历史
     * @return 告警历史
     */
    Page<HostAlarmHistoryDTO> pageHistory(HostAlarmHistoryPageQuery hostAlarmHistoryPageQuery);

}
