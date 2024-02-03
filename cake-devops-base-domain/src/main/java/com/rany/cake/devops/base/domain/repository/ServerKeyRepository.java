package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.entity.ServerKey;
import com.rany.cake.devops.base.domain.repository.param.ServerKeyQueryParam;

import javax.validation.constraints.NotNull;

public interface ServerKeyRepository {

    ServerKey find(@NotNull Long id);

    void remove(@NotNull ServerKey serverKey);

    void save(@NotNull ServerKey serverKey);

    int update(ServerKey serverKey);


    Page<ServerKey> pageServerKey(ServerKeyQueryParam serverKeyQueryParam);

}
