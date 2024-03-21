package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.entity.SystemEnv;
import com.rany.cake.devops.base.domain.repository.param.SystemEnvPageQueryParam;

import java.util.List;

public interface SystemEnvRepository {

    SystemEnv find(Long id);

    void remove(SystemEnv env);

    void save(SystemEnv env);

    void batchSave(List<SystemEnv> envs);

    void update(SystemEnv env);

    Page<SystemEnv> page(SystemEnvPageQueryParam queryParam);
}
