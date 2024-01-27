package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.cake.framework.ddd.repository.Repository;
import com.rany.cake.devops.base.domain.aggregate.ServerAccount;
import com.rany.cake.devops.base.domain.pk.ServerAccountId;
import com.rany.cake.devops.base.domain.repository.param.ServerAccountQueryParam;

public interface ServerAccountRepository extends Repository<ServerAccount, ServerAccountId> {

    int update(ServerAccount serverAccount);


    Page<ServerAccount> pageServerAccount(ServerAccountQueryParam serverAccountQueryParam);

}
