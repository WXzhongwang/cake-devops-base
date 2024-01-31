package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.cake.framework.ddd.repository.Repository;
import com.rany.cake.devops.base.domain.aggregate.ServerKey;
import com.rany.cake.devops.base.domain.pk.ServerKeyId;
import com.rany.cake.devops.base.domain.repository.param.ServerKeyQueryParam;

public interface ServerKeyRepository extends Repository<ServerKey, ServerKeyId> {

    int update(ServerKey serverKey);


    Page<ServerKey> pageServerKey(ServerKeyQueryParam serverKeyQueryParam);

}
