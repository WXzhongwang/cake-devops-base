package com.rany.cake.devops.base.domain.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.aggregate.ServerAccount;
import com.rany.cake.devops.base.domain.pk.ServerAccountId;
import com.rany.cake.devops.base.domain.repository.ServerAccountRepository;
import com.rany.cake.devops.base.domain.repository.param.ServerAccountQueryParam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * ServerAccountDomainService
 *
 * @author zhongshengwang
 * @description appDomainService
 * @date 2023/1/28 20:59
 * @email 18668485565163.com
 */
@Component
@AllArgsConstructor
public class ServerAccountDomainService {

    private final ServerAccountRepository serverAccountRepository;

    public void save(ServerAccount serverAccount) {
        serverAccountRepository.save(serverAccount);
    }

    public void update(ServerAccount serverAccount) {
        serverAccountRepository.update(serverAccount);
    }

    public ServerAccount getServerAccount(ServerAccountId serverAccountId) {
        return serverAccountRepository.find(serverAccountId);
    }

    public Page<ServerAccount> pageServerAccount(ServerAccountQueryParam serverAccountQueryParam) {
        return serverAccountRepository.pageServerAccount(serverAccountQueryParam);
    }
}
