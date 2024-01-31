package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.cake.framework.ddd.repository.Repository;
import com.rany.cake.devops.base.domain.aggregate.ServerProxy;
import com.rany.cake.devops.base.domain.pk.ServerProxyId;
import com.rany.cake.devops.base.domain.repository.param.ServerProxyQueryParam;

public interface ServerProxyRepository extends Repository<ServerProxy, ServerProxyId> {

    int update(ServerProxy serverKey);


    Page<ServerProxy> pageServerProxy(ServerProxyQueryParam serverProxyQueryParam);

}
