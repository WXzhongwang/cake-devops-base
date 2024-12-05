package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PojoResult;
import com.google.common.collect.Maps;
import com.rany.cake.devops.base.api.command.agent.InstallMonitorAgentCommand;
import com.rany.cake.devops.base.api.command.agent.SyncMonitorAgentCommand;
import com.rany.cake.devops.base.api.command.agent.UpdateMonitorAgentCommand;
import com.rany.cake.devops.base.api.dto.AppAccountDTO;
import com.rany.cake.devops.base.api.dto.HostDTO;
import com.rany.cake.devops.base.api.dto.HostMonitorDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.query.agent.HostMonitorPageQuery;
import com.rany.cake.devops.base.api.service.HostMonitorService;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.base.AppConfig;
import com.rany.cake.devops.base.domain.entity.HostMonitor;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostMonitorRepository;
import com.rany.cake.devops.base.domain.repository.HostRepository;
import com.rany.cake.devops.base.domain.repository.WebSideMessageRepository;
import com.rany.cake.devops.base.domain.repository.param.HostMonitorPageQueryParam;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.AppMemberAdapter;
import com.rany.cake.devops.base.service.adapter.HostDataAdapter;
import com.rany.cake.devops.base.service.adapter.HostMonitorDataAdapter;
import com.rany.cake.devops.base.service.handler.agent.MonitorAgents;
import com.rany.cake.devops.base.service.handler.host.HostConnectionService;
import com.rany.cake.devops.base.util.SchedulerPools;
import com.rany.cake.devops.base.util.enums.MonitorStatus;
import com.rany.cake.devops.base.util.system.SystemEnvAttr;
import com.rany.cake.toolkit.lang.Threads;
import com.rany.cake.toolkit.lang.io.Files1;
import com.rany.cake.toolkit.lang.utils.Strings;
import com.rany.ops.api.facade.account.AccountFacade;
import com.rany.ops.api.query.account.AccountBasicQuery;
import com.rany.ops.common.dto.account.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 主机监控服务
 *
 * @author zhongshengwang
 * @description 主机监控服务
 * @date 2023/1/28 20:40
 * @email 18668485565163.com
 */
@Service
@Slf4j
@AllArgsConstructor
public class HostMonitorServiceImpl implements HostMonitorService {

    private final HostMonitorRepository hostMonitorRepository;
    private final HostRepository hostRepository;
    private final HostMonitorDataAdapter hostMonitorDataAdapter;
    private final HostDataAdapter hostDataAdapter;
    private final MonitorAgents monitorAgents;
    private final HostConnectionService hostConnectionService;
    private final HostDomainService hostDomainService;
    private final WebSideMessageRepository webSideMessageService;
    private final AccountFacade accountFacade;
    private final AppMemberAdapter appMemberAdapter;
    private final AppConfig appConfig;

    @Override
    public Page<HostMonitorDTO> pageHostMonitor(HostMonitorPageQuery hostMonitorPageQuery) {
        HostMonitorPageQueryParam hostMonitorPageQueryParam = hostMonitorDataAdapter.convertParam(hostMonitorPageQuery);
        Page<Host> hostPage = hostRepository.queryMonitorHost(hostMonitorPageQueryParam);
        List<Host> items = new ArrayList<>(hostPage.getItems());
        List<HostDTO> hostDTOList = hostDataAdapter.sourceToTarget(items);
        List<String> hostIds = hostDTOList.stream().map(HostDTO::getHostId).collect(Collectors.toList());
        List<HostMonitor> hostMonitorList = hostMonitorRepository.findByHostId(hostIds);
        List<HostMonitorDTO> hostMonitorDTOList = hostMonitorDataAdapter.sourceToTarget(hostMonitorList);
        Map<String, HostDTO> hostDTOMap = Maps.uniqueIndex(hostDTOList, HostDTO::getHostId);
        for (HostMonitorDTO hostMonitorDTO : hostMonitorDTOList) {
            HostDTO hostDTO = hostDTOMap.get(hostMonitorDTO.getHostId());
            hostMonitorDTO.setHost(hostDTO);
        }
        return PageUtils.build(hostPage, hostMonitorDTOList);
    }

    @Override
    public HostMonitorDTO findByHostId(String hostId) {
        Host host = hostRepository.find(new HostId(hostId));
        HostDTO hostDTO = hostDataAdapter.sourceToTarget(host);
        HostMonitor monitor = hostMonitorRepository.findByHostId(hostId);
        HostMonitorDTO hostMonitorDTO = hostMonitorDataAdapter.sourceToTarget(monitor);
        hostMonitorDTO.setHost(hostDTO);
        return hostMonitorDTO;
    }

    @Override
    public Boolean installAgent(InstallMonitorAgentCommand command) {
        HostMonitor monitor = hostMonitorRepository.findByHostId(command.getHostId());
        Host host = hostDomainService.getHost(new HostId(command.getHostId()));
        if (Strings.eq(monitor.getMonitorStatus(), MonitorStatus.STARTING.getStatus())) {
            throw new BusinessException(DevOpsErrorMessage.AGENT_STATUS_RUNNING);
        }
        boolean reinstall = command.getUpgrade();
        if (!command.getUpgrade()) {
            // 同步并且获取插件版本
            String version = monitorAgents.syncMonitorAgent(command.getHostId(), monitor.getMonitorUrl(), monitor.getAccessToken());
            if (version == null) {
                // 未获取到版本则重新安装
                reinstall = true;
            } else {
                // 状态改为运行中
                monitor.setAgentVersion(version);
                monitor.setMonitorStatus(MonitorStatus.RUNNING.getStatus());
            }
        }
        if (reinstall) {
            // 重新安装
            String path = SystemEnvAttr.MACHINE_MONITOR_AGENT_PATH.getValue();
            if (!Files1.isFile(path)) {
                throw new BusinessException(DevOpsErrorMessage.AGENT_FILE_NON_EXIST.getErrorCode(),
                        Strings.format(DevOpsErrorMessage.AGENT_FILE_NON_EXIST.getMessage(), path));
            }
            // 状态改为启动中
            monitor.setMonitorStatus(MonitorStatus.STARTING.getStatus());
            // 创建安装任务
            AccountBasicQuery accountBasicQuery = new AccountBasicQuery();
            accountBasicQuery.setAccountId(Long.valueOf(command.getUser()));
            accountBasicQuery.setTenantId(appConfig.getTenantId());
            PojoResult<AccountDTO> account = accountFacade.getAccount(accountBasicQuery);
            AccountDTO content = account.getContent();
            AppAccountDTO appAccountDTO = appMemberAdapter.toDTO(content);
            Threads.start(new MonitorAgentInstallTask(hostConnectionService,
                            hostDomainService,
                            monitorAgents,
                            hostMonitorRepository,
                            webSideMessageService,
                            appAccountDTO,
                            host
                    ),
                    SchedulerPools.AGENT_INSTALL_SCHEDULER);
        }
        hostMonitorRepository.update(monitor);
        return Boolean.TRUE;
    }

    @Override
    public String syncAgent(SyncMonitorAgentCommand command) {
        return monitorAgents.syncMonitorAgent(command.getHostId(),
                command.getUrl(), command.getAccessToken());
    }

    @Override
    public Boolean updateMonitorConfig(UpdateMonitorAgentCommand command) {
        HostMonitor monitor = hostMonitorRepository.findByHostId(command.getHostId());
        monitor.setAccessToken(command.getAccessToken());
        monitor.setMonitorUrl(command.getUrl());
        // 同步状态
        if (monitor.getMonitorStatus().equals(MonitorStatus.NOT_START.getStatus()) ||
                monitor.getMonitorStatus().equals(MonitorStatus.RUNNING.getStatus())) {
            // 同步并且获取插件版本
            String monitorVersion = monitorAgents.syncMonitorAgent(command.getHostId(), command.getUrl(),
                    command.getAccessToken());
            if (monitorVersion == null) {
                // 未启动
                monitor.setMonitorStatus(MonitorStatus.NOT_START.getStatus());
            } else {
                monitor.setAgentVersion(monitorVersion);
                monitor.setMonitorStatus(MonitorStatus.RUNNING.getStatus());
            }
        }
        hostMonitorRepository.update(monitor);
        return Boolean.TRUE;
    }

    @Override
    public String getMonitorVersion(String url, String accessToken) {
        return monitorAgents.getMonitorVersion(url, accessToken);
    }

    @Override
    public HostMonitorDTO checkMonitorStatus(String hostId) {
        // 获取监控配置
        HostMonitor monitor = hostMonitorRepository.findByHostId(hostId);
        HostMonitorDTO hostMonitorDTO = hostMonitorDataAdapter.sourceToTarget(monitor);
        Host host = hostRepository.find(new HostId(hostId));
        HostDTO hostDTO = hostDataAdapter.sourceToTarget(host);
        hostMonitorDTO.setHost(hostDTO);

        // 启动中直接返回
        if (monitor.getMonitorStatus().equals(MonitorStatus.STARTING.getStatus())) {
            return hostMonitorDTO;
        }
        // 同步并且获取插件版本
        String monitorVersion = monitorAgents.syncMonitorAgent(hostId,
                monitor.getMonitorUrl(), monitor.getAccessToken());
        if (monitorVersion == null) {
            // 未启动
            monitor.setMonitorStatus(MonitorStatus.NOT_START.getStatus());
        } else {
            // 启动中
            monitor.setAgentVersion(monitorVersion);
            monitor.setMonitorStatus(MonitorStatus.RUNNING.getStatus());
        }
        hostMonitorRepository.update(monitor);
        return hostMonitorDTO;
    }

}
