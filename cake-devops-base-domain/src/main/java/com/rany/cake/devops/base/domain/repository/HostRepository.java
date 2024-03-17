package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.cake.framework.ddd.repository.Repository;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.entity.GroupHost;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.param.HostPageQueryParam;

import java.util.List;

/**
 * 主机
 *
 * @author zhongshengwang
 * @description 主机
 * @date 2023/1/28 21:01
 * @email 18668485565163.com
 */
public interface HostRepository extends Repository<Host, HostId> {

    int update(Host host);

    List<Host> findByIds(List<String> hostIds);

    List<Host> getHostsByGroupIds(List<String> groupIds);

    List<GroupHost> getGroupHostByHostId(HostId hostId);

    Page<Host> pageHost(HostPageQueryParam hostPageQueryParam);

    void saveGroupHosts(List<GroupHost> groupHosts);
}
