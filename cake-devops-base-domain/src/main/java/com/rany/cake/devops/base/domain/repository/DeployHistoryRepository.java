package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.entity.DeployHistory;
import com.rany.cake.devops.base.domain.repository.param.DeployHistoryPageParam;

public interface DeployHistoryRepository {
    DeployHistory find(Long id);

    void save(DeployHistory log);

    int update(DeployHistory log);

    Page<DeployHistory> pageQuery(DeployHistoryPageParam param);
}
