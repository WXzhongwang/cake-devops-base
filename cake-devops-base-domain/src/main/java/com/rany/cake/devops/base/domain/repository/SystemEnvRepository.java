package com.rany.cake.devops.base.domain.repository;

import com.rany.cake.devops.base.domain.entity.SystemEnv;

public interface SystemEnvRepository {

    SystemEnv find(Long id);

    void remove(SystemEnv env);

    void save(SystemEnv env);
}
