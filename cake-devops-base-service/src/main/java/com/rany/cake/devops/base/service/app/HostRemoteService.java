package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.host.CreateHostCommand;
import com.rany.cake.devops.base.api.command.host.DeleteHostCommand;
import com.rany.cake.devops.base.api.command.host.ModifyHostCommand;
import com.rany.cake.devops.base.api.dto.HostDTO;
import com.rany.cake.devops.base.api.query.HostBasicQuery;
import com.rany.cake.devops.base.api.service.HostService;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.service.adapter.HostDataAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

/**
 * 主机服务
 *
 * @author zhongshengwang
 * @description 主机服务
 * @date 2023/1/28 20:40
 * @email 18668485565163.com
 */
@Service
@ShenyuService("/host/**")
@Slf4j
@AllArgsConstructor
public class HostRemoteService implements HostService {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final HostDomainService hostDomainService;
    private final HostDataAdapter hostDataAdapter;


    @Override
    public PojoResult<String> createHostCommand(CreateHostCommand createHostCommand) {
        Host host = new Host(new HostId(String.valueOf(snowflakeIdWorker.nextId())), createHostCommand.getName(),
                createHostCommand.getHostname(), createHostCommand.getPort());
        host.setUsername(createHostCommand.getUsername());
        host.setPkey(createHostCommand.getPkey());
        hostDomainService.save(host);
        return PojoResult.succeed(host.getHostId().getHostId());
    }

    @Override
    public PojoResult<HostDTO> getHost(HostBasicQuery hostBasicQuery) {
        Host host = hostDomainService.getHost(new HostId(hostBasicQuery.getHostId()));
        return PojoResult.succeed(hostDataAdapter.sourceToTarget(host));
    }

    @Override
    public PojoResult<Boolean> deleteHost(DeleteHostCommand deleteHostCommand) {
        Host host = hostDomainService.getHost(new HostId(deleteHostCommand.getHostId()));
        host.delete();
        hostDomainService.update(host);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> modifyHost(ModifyHostCommand modifyHostCommand) {
        Host host = hostDomainService.getHost(new HostId(modifyHostCommand.getHostId()));
        host.setHostName(modifyHostCommand.getHostname());
        host.setName(modifyHostCommand.getName());
        host.setPort(modifyHostCommand.getPort());
        host.setUsername(modifyHostCommand.getUsername());
        host.setPkey(modifyHostCommand.getPkey());
        host.modify();
        hostDomainService.update(host);
        return PojoResult.succeed(Boolean.TRUE);
    }
}
