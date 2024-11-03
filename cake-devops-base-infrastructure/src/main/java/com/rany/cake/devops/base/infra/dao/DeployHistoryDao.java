package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.DeployHistory;
import com.rany.cake.devops.base.domain.repository.param.DeployHistoryPageParam;
import com.rany.cake.devops.base.infra.po.DeployHistoryPO;

import java.util.List;

public interface DeployHistoryDao {

    /**
     * 新增
     *
     * @param history 日志
     * @return
     */
    int save(DeployHistory history);


    /**
     * 根据id查询传输日志
     *
     * @param history 日志
     */
    void update(DeployHistory history);


    List<DeployHistoryPO> pageQuery(DeployHistoryPageParam param);
}
