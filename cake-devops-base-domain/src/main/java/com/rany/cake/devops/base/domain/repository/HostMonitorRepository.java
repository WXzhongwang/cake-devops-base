package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.entity.HostMonitor;
import com.rany.cake.devops.base.domain.repository.param.HostMonitorPageQueryParam;

import java.util.List;

public interface HostMonitorRepository {

    HostMonitor find(Long id);

    HostMonitor findByHostId(String hostId);

    List<HostMonitor> findByHostId(List<String> hostIds);

    void remove(HostMonitor hostMonitor);

    void save(HostMonitor hostMonitor);

    void update(HostMonitor hostMonitor);

    Page<HostMonitor> page(HostMonitorPageQueryParam queryParam);
}
