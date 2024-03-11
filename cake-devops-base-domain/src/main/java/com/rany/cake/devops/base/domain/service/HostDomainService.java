package com.rany.cake.devops.base.domain.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.aggregate.HostGroup;
import com.rany.cake.devops.base.domain.entity.GroupHost;
import com.rany.cake.devops.base.domain.entity.HostEnv;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostEnvRepository;
import com.rany.cake.devops.base.domain.repository.HostGroupRepository;
import com.rany.cake.devops.base.domain.repository.HostRepository;
import com.rany.cake.devops.base.domain.repository.param.HostEnvQueryParam;
import com.rany.cake.devops.base.domain.repository.param.HostPageQueryParam;
import com.rany.cake.devops.base.util.MachineConst;
import com.rany.cake.devops.base.util.enums.MachineEnvAttr;
import com.rany.cake.toolkit.lang.utils.Strings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    private final HostEnvRepository hostEnvRepository;
    private final HostGroupRepository hostGroupRepository;

    public List<Host> getPackageMachineList() {
        HostGroup packagingGroup = hostGroupRepository.getPackagingGroup();
        if (packagingGroup == null) {
            return Collections.emptyList();
        }
        List<String> groupIds = new ArrayList<>();
        groupIds.add(packagingGroup.getBizID().getHostGroupId());
        return hostRepository.getHostsByGroupIds(groupIds);
    }

    /**
     * 创建主机
     *
     * @param host
     */
    public void save(Host host, List<GroupHost> groupHosts) {
        hostRepository.save(host);
        hostRepository.saveGroupHosts(groupHosts);
    }

    public void update(Host host) {
        hostRepository.update(host);
    }

    public Host getHost(HostId hostId) {
        return hostRepository.find(hostId);
    }

    public List<GroupHost> getGroupHost(HostId hostId) {
        return hostRepository.getGroupHostByHostId(hostId);
    }

    public Page<Host> pageHost(HostPageQueryParam hostPageQueryParam) {
        return hostRepository.pageHost(hostPageQueryParam);
    }

    public Integer getConnectionTimeout(HostId hostId) {
        HostEnvQueryParam queryParam = new HostEnvQueryParam();
        queryParam.setHostId(hostId.getHostId());
        List<HostEnv> list = hostEnvRepository.list(queryParam);
        HostEnv hostEnv = list.stream().filter(p -> Strings.eq(MachineEnvAttr.CONNECT_TIMEOUT.getKey(), p.getAttrKey()))
                .findFirst().orElse(null);
        String value = Optional.ofNullable(hostEnv).map(HostEnv::getAttrValue).orElse(Strings.EMPTY);
        int timeout = Strings.isInteger(value) ? Integer.parseInt(value) : MachineConst.CONNECT_TIMEOUT;
        return Math.max(timeout, 0);
    }
}
