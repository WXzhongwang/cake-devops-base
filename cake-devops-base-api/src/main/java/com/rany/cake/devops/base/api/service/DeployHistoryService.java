package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.dto.DeployHistoryDTO;
import com.rany.cake.devops.base.api.dto.DeployLogDTO;
import com.rany.cake.devops.base.api.query.release.DeployHistoryPageQuery;

import java.util.List;

public interface DeployHistoryService {


    /**
     * 查询部署日志
     *
     * @param pipeKey 追踪key
     * @param from    beginTime
     * @param to      endTime
     * @return 日志明细
     */
    List<DeployLogDTO> queryDeployLog(String pipeKey, long from, long to);

    /**
     * 查看部署历史
     *
     * @param deployHistoryPageQuery 查看部署历史
     * @return 部署历史
     */
    Page<DeployHistoryDTO> queryDeployHistory(DeployHistoryPageQuery deployHistoryPageQuery);
}
