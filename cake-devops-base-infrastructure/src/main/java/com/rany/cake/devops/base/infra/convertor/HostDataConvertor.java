package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.entity.GroupHost;
import com.rany.cake.devops.base.infra.po.GroupHostPO;
import com.rany.cake.devops.base.infra.po.HostPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * 主机
 *
 * @author zhongshengwang
 * @description 主机
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface HostDataConvertor extends BaseConvertor<Host, HostPO> {


    /**
     * 聚合根转PO
     *
     * @param host 聚合根
     * @return PO
     */
    @Mapping(source = "hostId.hostId", target = "hostId")
    @Override
    HostPO sourceToTarget(Host host);

    GroupHostPO convert(GroupHost groupHost);

    List<GroupHostPO> convert(List<GroupHost> groupHosts);

    /**
     * PO转聚合根
     *
     * @param hostPO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "hostId.hostId", source = "hostId")
    Host targetToSource(HostPO hostPO);

    GroupHost reconvert(GroupHostPO groupHost);

    List<GroupHost> reconvert(List<GroupHostPO> groupHosts);
}
