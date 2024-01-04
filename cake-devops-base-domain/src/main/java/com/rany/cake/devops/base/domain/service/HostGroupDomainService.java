package com.rany.cake.devops.base.domain.service;

import com.google.common.collect.Lists;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.aggregate.HostGroup;
import com.rany.cake.devops.base.domain.pk.HostGroupId;
import com.rany.cake.devops.base.domain.repository.HostGroupRepository;
import com.rany.cake.devops.base.domain.repository.HostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 集群
 *
 * @author zhongshengwang
 * @description 集群
 * @date 2023/2/18 22:40
 * @email 18668485565163.com
 */
@Component
@AllArgsConstructor
public class HostGroupDomainService {
    private final HostRepository hostRepository;
    private final HostGroupRepository hostGroupRepository;


    /**
     * 创建主机组
     *
     * @param hostGroup
     */
    public void save(HostGroup hostGroup) {
        hostGroupRepository.save(hostGroup);
    }

    public void update(HostGroup hostGroup) {
        hostGroupRepository.update(hostGroup);
    }

    public HostGroup getHostGroup(HostGroupId hostGroupId) {
        HostGroup hostGroup = hostGroupRepository.find(hostGroupId);
        List<Host> hosts = hostRepository.getHostsByGroupIds(Lists.newArrayList(hostGroup.getBizID().getHostGroupId()));
        hostGroup.setHosts(hosts);
        return hostGroup;
    }

    public List<HostGroup> listAllHostGroup() {
        return hostGroupRepository.listAll();
    }

}
