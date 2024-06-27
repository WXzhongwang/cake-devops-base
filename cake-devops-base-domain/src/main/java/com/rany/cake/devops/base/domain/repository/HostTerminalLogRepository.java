package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.entity.HostTerminalLog;
import com.rany.cake.devops.base.domain.repository.param.TerminalLogPageQueryParam;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface HostTerminalLogRepository {
    HostTerminalLog getConfig(String accessToken);

    void remove(@NotNull HostTerminalLog log);

    Long save(@NotNull HostTerminalLog log);

    int update(HostTerminalLog log);

    int deleteByIds(List<Long> ids);

    Page<HostTerminalLog> pageQueryTerminalLog(TerminalLogPageQueryParam param);
}
