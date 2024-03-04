package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.entity.HostEnv;
import com.rany.cake.devops.base.domain.repository.param.HostEnvQueryParam;

public interface HostEnvRepository {
    HostEnv find(Long id);

    void remove(HostEnv env);

    void save(HostEnv env);

    void update(HostEnv env);

    Page<HostEnv> page(HostEnvQueryParam queryParam);
}
