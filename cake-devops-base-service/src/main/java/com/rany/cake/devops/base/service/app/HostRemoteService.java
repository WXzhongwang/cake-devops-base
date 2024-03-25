package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.host.*;
import com.rany.cake.devops.base.api.dto.HostDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.query.HostBasicQuery;
import com.rany.cake.devops.base.api.query.HostPageQuery;
import com.rany.cake.devops.base.api.service.HostService;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.entity.GroupHost;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.param.HostPageQueryParam;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.HostDataAdapter;
import com.rany.cake.devops.base.service.base.Constants;
import com.rany.cake.toolkit.lang.net.IPs;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.ArrayList;
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
@ShenyuService("/host/**")
@Slf4j
@AllArgsConstructor
public class HostRemoteService implements HostService {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final HostDomainService hostDomainService;
    private final HostDataAdapter hostDataAdapter;


    @Override
    public String createHost(CreateHostCommand createHostCommand) {
        Host host = new Host(new HostId(String.valueOf(snowflakeIdWorker.nextId())), createHostCommand.getName(),
                createHostCommand.getHostName(), createHostCommand.getServerAddr(), createHostCommand.getPort());
        host.setUsername(createHostCommand.getUsername());
        host.setPwd(createHostCommand.getPwd());
        host.setKeyId(createHostCommand.getKeyId());
        host.setProxyId(createHostCommand.getProxyId());

        List<GroupHost> groupHosts = new ArrayList<>();
        for (String hostGroupId : createHostCommand.getHostGroupIds()) {
            GroupHost groupHost = new GroupHost(hostGroupId, host.getHostId().getHostId());
            groupHosts.add(groupHost);
        }
        hostDomainService.save(host, groupHosts);
        return host.getHostId().getHostId();
    }

    @Override
    public String copyHost(CopyHostCommand copyHostCommand) {
        HostId hostId = new HostId(copyHostCommand.getHostId());
        Host host = hostDomainService.getHost(hostId);
        List<GroupHost> groupHost = hostDomainService.getGroupHost(hostId);
        HostId newHostId = new HostId(String.valueOf(snowflakeIdWorker.nextId()));
        host.copy(newHostId, groupHost);
        hostDomainService.save(host, groupHost);
        return host.getHostId().getHostId();
    }

    @Override
    public String ping(PingHostCommand pingHostCommand) {
        // 查询超时时间
        Host host = hostDomainService.getHost(new HostId(pingHostCommand.getHostId()));
        Integer connectTimeout = hostDomainService.getConnectionTimeout(new HostId(pingHostCommand.getHostId()));
        if (!IPs.ping(host.getServerAddr(), connectTimeout)) {
            throw new BusinessException(DevOpsErrorMessage.OPS_CONNECTED_ERROR);
        }
        return Constants.PONG;
    }

    @Override
    public HostDTO getHost(HostBasicQuery hostBasicQuery) {
        Host host = hostDomainService.getHost(new HostId(hostBasicQuery.getHostId()));
        return hostDataAdapter.sourceToTarget(host);
    }

    @Override
    public Page<HostDTO> pageHost(HostPageQuery hostPageQuery) {
        HostPageQueryParam hostPageQueryParam = hostDataAdapter.convertParam(hostPageQuery);
        Page<Host> page = hostDomainService.pageHost(hostPageQueryParam);
        List<Host> hosts = new ArrayList<>(page.getItems());
        List<HostDTO> hostDTOList = hostDataAdapter.sourceToTarget(hosts);
        return PageUtils.build(page, hostDTOList);
    }

    @Override
    public Boolean deleteHost(DeleteHostCommand deleteHostCommand) {
        Host host = hostDomainService.getHost(new HostId(deleteHostCommand.getHostId()));
        host.delete();
        hostDomainService.update(host);
        return Boolean.TRUE;
    }

    @Override
    public Boolean modifyHost(ModifyHostCommand modifyHostCommand) {
        Host host = hostDomainService.getHost(new HostId(modifyHostCommand.getHostId()));
        host.setHostName(modifyHostCommand.getHostname());
        host.setName(modifyHostCommand.getName());
        host.setPort(modifyHostCommand.getPort());
        host.setUsername(modifyHostCommand.getUsername());
        host.setPwd(modifyHostCommand.getPkey());
        host.setKeyId(modifyHostCommand.getKeyId());
        host.setProxyId(modifyHostCommand.getProxyId());
        host.modify();
        hostDomainService.update(host);
        return Boolean.TRUE;
    }
}
