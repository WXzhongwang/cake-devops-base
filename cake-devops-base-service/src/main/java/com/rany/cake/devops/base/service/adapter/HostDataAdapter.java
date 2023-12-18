package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.HostDTO;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import com.rany.cake.devops.base.service.plugins.machine.Machine;
import org.mapstruct.InheritConfiguration;
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
public interface HostDataAdapter extends BaseConvertor<Host, HostDTO> {


    /**
     * 聚合根转PO
     *
     * @param host 聚合根
     * @return PO
     */
    @Mapping(source = "hostId.hostId", target = "hostId")
    HostDTO sourceToTarget(Host host);

    @InheritConfiguration(name = "sourceToTarget")
    List<HostDTO> sourceToTarget(List<Host> hosts);

    /**
     * PO转聚合根
     *
     * @param hostDTO PO
     * @return 聚合根
     */

    @Mapping(target = "hostId.hostId", source = "hostId")
    Host targetToSource(HostDTO hostDTO);

    @InheritConfiguration(name = "targetToSource")
    List<Host> targetToSource(List<HostDTO> hostDTO);

    @Mapping(source = "hostId.hostId", target = "hostId")
    Machine sourceToMachine(Host host);

    @InheritConfiguration(name = "sourceToMachine")
    List<Machine> sourceToMachine(List<Host> hosts);
}
