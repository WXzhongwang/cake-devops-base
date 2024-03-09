package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.entity.HostEnv;
import com.rany.cake.devops.base.domain.repository.param.HostEnvQueryParam;

import java.util.List;
import java.util.Map;

public interface HostEnvRepository {
    HostEnv find(Long id);

    void remove(HostEnv env);

    void save(HostEnv env);

    void update(HostEnv env);

    Page<HostEnv> page(HostEnvQueryParam queryParam);

    List<HostEnv> list(HostEnvQueryParam queryParam);

    void saveEnv(String hostId, Map<String, String> attributes);
}
