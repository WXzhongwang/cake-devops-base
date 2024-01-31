package com.rany.cake.devops.base.domain.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.aggregate.ServerKey;
import com.rany.cake.devops.base.domain.pk.ServerKeyId;
import com.rany.cake.devops.base.domain.repository.ServerKeyRepository;
import com.rany.cake.devops.base.domain.repository.param.ServerKeyQueryParam;
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
public class ServerKeyDomainService {

    private final ServerKeyRepository serverKeyRepository;

    public void save(ServerKey serverKey) {
        serverKeyRepository.save(serverKey);
    }

    public void update(ServerKey serverKey) {
        serverKeyRepository.update(serverKey);
    }

    public ServerKey getServerKey(ServerKeyId serverKeyId) {
        return serverKeyRepository.find(serverKeyId);
    }

    public Page<ServerKey> pageServerAccount(ServerKeyQueryParam serverKeyQueryParam) {
        return serverKeyRepository.pageServerKey(serverKeyQueryParam);
    }
}
