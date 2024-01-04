package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.HostGroupDTO;
import com.rany.cake.devops.base.api.dto.HostGroupTreeDTO;
import com.rany.cake.devops.base.domain.aggregate.HostGroup;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * 主机组
 *
 * @author zhongshengwang
 * @description 主机组
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface HostGroupDataAdapter extends BaseConvertor<HostGroup, HostGroupDTO> {


    /**
     * 聚合根转PO
     *
     * @param host 聚合根
     * @return PO
     */
    @Mapping(source = "hostGroupId.hostGroupId", target = "hostGroupId")
    HostGroupDTO sourceToTarget(HostGroup host);

    @InheritConfiguration(name = "sourceToTarget")
    List<HostGroupDTO> sourceToTarget(List<HostGroup> hosts);

    /**
     * PO转聚合根
     *
     * @param hostDTO PO
     * @return 聚合根
     */

    @Mapping(target = "hostGroupId.hostGroupId", source = "hostGroupId")
    HostGroup targetToSource(HostGroupDTO hostDTO);

    @InheritConfiguration(name = "targetToSource")
    List<HostGroup> targetToSource(List<HostGroupDTO> hostDTO);


    HostGroupTreeDTO toTreeDTO(HostGroupDTO hostDTO);

    @InheritConfiguration(name = "toTreeDTO")
    List<HostGroupTreeDTO> toTreeDTO(List<HostGroupDTO> hostGroupDTOList);
}
