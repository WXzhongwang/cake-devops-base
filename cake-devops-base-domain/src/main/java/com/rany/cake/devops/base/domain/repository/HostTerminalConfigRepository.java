package com.rany.cake.devops.base.domain.repository;

import com.rany.cake.devops.base.domain.entity.HostTerminalConfig;

import javax.validation.constraints.NotNull;

public interface HostTerminalConfigRepository {
    HostTerminalConfig getConfig(String hostId);

    void remove(@NotNull HostTerminalConfig config);

    void save(@NotNull HostTerminalConfig config);

    int update(HostTerminalConfig config);
}
