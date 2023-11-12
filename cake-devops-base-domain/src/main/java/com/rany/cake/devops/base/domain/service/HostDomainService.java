package com.rany.cake.devops.base.domain.service;

import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostGroupRepository;
import com.rany.cake.devops.base.domain.repository.HostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 主机
 *
 * @author zhongshengwang
 * @description 主机
 * @date 2023/2/18 22:40
 * @email 18668485565163.com
 */
@Component
@AllArgsConstructor
public class HostDomainService {
    private final HostRepository hostRepository;
    private final HostGroupRepository hostGroupRepository;


    /**
     * 创建主机
     *
     * @param host
     */
    public void save(Host host) {
        hostRepository.save(host);
    }

    public void update(Host host) {
        hostRepository.update(host);
    }

    public Host getHost(HostId hostId) {
        return hostRepository.find(hostId);
    }
}
