package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.entity.ServerProxy;
import com.rany.cake.devops.base.domain.repository.param.ServerProxyQueryParam;

import javax.validation.constraints.NotNull;

public interface ServerProxyRepository {
    ServerProxy find(@NotNull Long proxyId);

    void remove(@NotNull ServerProxy serverProxy);

    void save(@NotNull ServerProxy serverProxy);

    int update(ServerProxy serverKey);

    Page<ServerProxy> pageServerProxy(ServerProxyQueryParam serverProxyQueryParam);
}
