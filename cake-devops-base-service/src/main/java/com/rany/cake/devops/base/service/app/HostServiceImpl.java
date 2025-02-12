package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.host.*;
import com.rany.cake.devops.base.api.dto.HostDTO;
import com.rany.cake.devops.base.api.dto.HostGroupDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.query.host.HostBasicQuery;
import com.rany.cake.devops.base.api.query.host.HostPageQuery;
import com.rany.cake.devops.base.api.service.HostService;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.aggregate.HostGroup;
import com.rany.cake.devops.base.domain.base.MonitorConst;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.entity.GroupHost;
import com.rany.cake.devops.base.domain.entity.HostMonitor;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostMonitorRepository;
import com.rany.cake.devops.base.domain.repository.HostRepository;
import com.rany.cake.devops.base.domain.repository.param.HostPageQueryParam;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.HostDataAdapter;
import com.rany.cake.devops.base.service.adapter.HostGroupDataAdapter;
import com.rany.cake.devops.base.service.base.Constants;
import com.rany.cake.devops.base.service.base.ValueMix;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.util.enums.MachineAuthType;
import com.rany.cake.devops.base.util.enums.MonitorStatus;
import com.rany.cake.toolkit.lang.net.IPs;
import com.rany.cake.toolkit.lang.utils.Strings;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 主机服务
 *
 * @author zhongshengwang
 * @description 主机服务
 * @date 2023/1/28 20:40
 * @email 18668485565163.com
 */
@Service
@Slf4j
@AllArgsConstructor
public class HostServiceImpl implements HostService {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final HostDomainService hostDomainService;
    private final HostDataAdapter hostDataAdapter;
    private final HostGroupDataAdapter hostGroupDataAdapter;
    private final HostMonitorRepository hostMonitorRepository;
    private final HostRepository hostRepository;


    @Override
    public String createHost(CreateHostCommand createHostCommand) {
        Host host = new Host(new HostId(String.valueOf(snowflakeIdWorker.nextId())), createHostCommand.getName(),
                createHostCommand.getHostName(), createHostCommand.getServerAddr(), createHostCommand.getPort());
        host.setUsername(createHostCommand.getUsername());

        host.setKeyId(createHostCommand.getKeyId());
        host.setProxyId(createHostCommand.getProxyId());
        host.setAuthType(createHostCommand.getAuthType());

        // 加密
        if (MachineAuthType.PASSWORD.getType().equals(createHostCommand.getAuthType())) {
            String password = createHostCommand.getPwd();
            if (Strings.isNotBlank(password)) {
                host.setPwd(ValueMix.encrypt(password));
            }
        }

        List<GroupHost> groupHosts = new ArrayList<>();
        for (String hostGroupId : createHostCommand.getHostGroupIds()) {
            GroupHost groupHost = new GroupHost(hostGroupId, host.getHostId().getHostId());
            groupHost.init(createHostCommand.getUser());
            groupHosts.add(groupHost);
        }
        host.init(createHostCommand.getUser());

        HostMonitor monitor = new HostMonitor();
        monitor.setHostId(host.getHostId().getHostId());
        monitor.setMonitorStatus(MonitorStatus.NOT_START.getStatus());
        monitor.setMonitorUrl(Strings.format(MonitorConst.DEFAULT_URL_FORMAT, host.getServerAddr()));
        monitor.setAccessToken(MonitorConst.DEFAULT_ACCESS_TOKEN);
        monitor.setIsDeleted(DeleteStatusEnum.NO.getValue());
        monitor.setAccessToken(MonitorConst.DEFAULT_ACCESS_TOKEN);
        monitor.init(createHostCommand.getUser());
        hostDomainService.save(host, groupHosts, monitor);
        return host.getHostId().getHostId();
    }

    @Override
    public String copyHost(CopyHostCommand copyHostCommand) {
        HostId hostId = new HostId(copyHostCommand.getHostId());
        Host host = hostDomainService.getHost(hostId);
        List<GroupHost> groupHost = hostDomainService.getGroupHost(hostId);
        HostMonitor monitor = hostMonitorRepository.findByHostId(copyHostCommand.getHostId());
        HostId newHostId = new HostId(String.valueOf(snowflakeIdWorker.nextId()));
        host.copy(newHostId, groupHost, monitor);
        hostDomainService.save(host, groupHost, monitor);
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
        for (HostDTO host : hostDTOList) {
            List<GroupHost> groupHost = hostDomainService.getGroupHost(new HostId(host.getHostId()));
            if (CollectionUtils.isNotEmpty(groupHost)) {
                List<String> hostGroupIds = groupHost.stream().map(GroupHost::getGroupId).collect(Collectors.toList());
                List<HostGroup> hostGroups = hostDomainService.getHostGroupByGroupIds(hostGroupIds);
                List<HostGroupDTO> hostGroupDTOS = hostGroupDataAdapter.sourceToTarget(hostGroups);
                host.setGroups(hostGroupDTOS);
            }
        }
        return PageUtils.build(page, hostDTOList);
    }

    @Override
    public Boolean deleteHost(DeleteHostCommand deleteHostCommand) {
        Host host = hostDomainService.getHost(new HostId(deleteHostCommand.getHostId()));
        host.delete(deleteHostCommand.getUser());

        // 删除主机监控
        HostMonitor hostMonitor = hostMonitorRepository.findByHostId(deleteHostCommand.getHostId());
        hostMonitor.delete(deleteHostCommand.getUser());

        // 删除机组关联关系
        List<GroupHost> groupHost = hostDomainService.getGroupHost(host.getHostId());
        groupHost.forEach(p -> {
            p.delete(deleteHostCommand.getUser());
        });


        hostDomainService.update(host);
        hostRepository.updateGroupHosts(groupHost);
        hostMonitorRepository.update(hostMonitor);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean modifyHost(ModifyHostCommand modifyHostCommand) {
        Host host = hostDomainService.getHost(new HostId(modifyHostCommand.getHostId()));
        host.setHostName(modifyHostCommand.getHostName());
        host.setName(modifyHostCommand.getName());
        host.setPort(modifyHostCommand.getPort());
        host.setUsername(modifyHostCommand.getUsername());

        // 加密
        if (MachineAuthType.PASSWORD.getType().equals(modifyHostCommand.getAuthType())) {
            String password = modifyHostCommand.getPwd();
            // 密码不一致才需要更新
            if (!StringUtils.equals(password, host.getPwd()) && Strings.isNotBlank(password)) {
                host.setPwd(ValueMix.encrypt(password));
            }
        }

        // 删除机组关联关系
        List<GroupHost> groupHost = hostDomainService.getGroupHost(host.getHostId());
        groupHost.forEach(p -> {
            p.delete(modifyHostCommand.getUser());
        });
        hostRepository.updateGroupHosts(groupHost);

        // 写入新的关联关系
        List<GroupHost> newGroupHost = new ArrayList<>();
        for (String hostGroupId : modifyHostCommand.getHostGroupIds()) {
            GroupHost newRel = new GroupHost(hostGroupId, host.getHostId().getHostId());
            newRel.init(modifyHostCommand.getUser());
            newGroupHost.add(newRel);
        }
        hostRepository.saveGroupHosts(newGroupHost);

        host.setKeyId(modifyHostCommand.getKeyId());
        host.setProxyId(modifyHostCommand.getProxyId());
        host.setServerAddr(modifyHostCommand.getServerAddr());
        host.modify(modifyHostCommand.getUser());
        hostDomainService.update(host);
        return Boolean.TRUE;
    }
}
