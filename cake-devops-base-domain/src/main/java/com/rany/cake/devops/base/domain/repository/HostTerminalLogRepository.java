package com.rany.cake.devops.base.domain.repository;

import com.rany.cake.devops.base.domain.entity.HostTerminalLog;

import javax.validation.constraints.NotNull;

public interface HostTerminalLogRepository {
    HostTerminalLog getConfig(String accessToken);

    void remove(@NotNull HostTerminalLog log);

    Long save(@NotNull HostTerminalLog log);

    int update(HostTerminalLog log);
}
