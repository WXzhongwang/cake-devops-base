package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.group.CreateGroupCommand;
import com.rany.cake.devops.base.api.command.group.DeleteGroupCommand;
import com.rany.cake.devops.base.api.command.group.ModifyGroupCommand;
import com.rany.cake.devops.base.api.dto.HostGroupDTO;
import com.rany.cake.devops.base.api.dto.HostGroupTreeDTO;
import com.rany.cake.devops.base.api.query.HostGroupBasicQuery;
import com.rany.cake.devops.base.api.query.HostGroupTreeQuery;
import com.rany.cake.devops.base.api.service.HostGroupService;
import com.rany.cake.devops.base.domain.aggregate.HostGroup;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.pk.HostGroupId;
import com.rany.cake.devops.base.domain.service.HostGroupDomainService;
import com.rany.cake.devops.base.service.adapter.HostGroupDataAdapter;
import com.rany.cake.devops.base.service.utils.HostGroupTreeConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.List;

/**
 * 主机服务
 *
 * @author zhongshengwang
 * @description 主机服务
 * @date 2023/1/28 20:40
 * @email 18668485565163.com
 */
@Service
@ShenyuService("/host-group/**")
@Slf4j
@AllArgsConstructor
public class HostGroupRemoteService implements HostGroupService {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final HostGroupDomainService hostGroupDomainService;
    private final HostGroupDataAdapter hostGroupDataAdapter;


    @Override
    public PojoResult<String> createHostGroup(CreateGroupCommand createHostCommand) {
        HostGroup host = new HostGroup(new HostGroupId(String.valueOf(snowflakeIdWorker.nextId())), createHostCommand.getName(), createHostCommand.getPid(),
                createHostCommand.getSort());
        hostGroupDomainService.save(host);
        return PojoResult.succeed(host.getBizID().getHostGroupId());
    }

    @Override
    public ListResult<HostGroupTreeDTO> getHostGroupTree(HostGroupTreeQuery hostGroupTreeQuery) {
        List<HostGroup> hostGroups = hostGroupDomainService.listAllHostGroup();
        List<HostGroupDTO> hostGroupDTOS = hostGroupDataAdapter.sourceToTarget(hostGroups);
        List<HostGroupTreeDTO> treeDTOS = hostGroupDataAdapter.toTreeDTO(hostGroupDTOS);
        List<HostGroupTreeDTO> hostGroupTreeDTOS = HostGroupTreeConverter.convertListToTree(treeDTOS);
        return ListResult.succeed(hostGroupTreeDTOS);
    }

    @Override
    public PojoResult<HostGroupDTO> getHostGroup(HostGroupBasicQuery hostGroupBasicQuery) {
        HostGroup hostGroup = hostGroupDomainService.getHostGroup(new HostGroupId(hostGroupBasicQuery.getHostGroupId()));
        return PojoResult.succeed(hostGroupDataAdapter.sourceToTarget(hostGroup));
    }

    @Override
    public PojoResult<Boolean> deleteHostGroup(DeleteGroupCommand deleteGroupCommand) {
        HostGroup hostGroup = hostGroupDomainService.getHostGroup(new HostGroupId(deleteGroupCommand.getGroupId()));
        hostGroup.delete();
        hostGroupDomainService.update(hostGroup);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> modifyHostGroup(ModifyGroupCommand modifyGroupCommand) {
        HostGroup hostGroup = hostGroupDomainService.getHostGroup(new HostGroupId(modifyGroupCommand.getGroupId()));
        hostGroup.setName(modifyGroupCommand.getName());
        hostGroup.setSort(modifyGroupCommand.getSort());
        hostGroup.setParentId(modifyGroupCommand.getParentId());
        hostGroup.modify();
        hostGroupDomainService.update(hostGroup);
        return PojoResult.succeed(Boolean.TRUE);
    }
}
