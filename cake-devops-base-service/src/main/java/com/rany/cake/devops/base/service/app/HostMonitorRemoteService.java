package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.google.common.collect.Maps;
import com.rany.cake.devops.base.api.command.monitor.InstallMonitorAgentCommand;
import com.rany.cake.devops.base.api.command.monitor.SyncMonitorAgentCommand;
import com.rany.cake.devops.base.api.command.monitor.UpdateMonitorAgentCommand;
import com.rany.cake.devops.base.api.dto.HostDTO;
import com.rany.cake.devops.base.api.dto.HostMonitorDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.query.HostMonitorPageQuery;
import com.rany.cake.devops.base.api.service.HostMonitorService;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.entity.HostMonitor;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostMonitorRepository;
import com.rany.cake.devops.base.domain.repository.HostRepository;
import com.rany.cake.devops.base.domain.repository.param.HostMonitorPageQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.HostDataAdapter;
import com.rany.cake.devops.base.service.adapter.HostMonitorDataAdapter;
import com.rany.cake.devops.base.service.base.MonitorConst;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.util.enums.MonitorStatus;
import com.rany.cake.toolkit.lang.utils.Strings;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

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
@ShenyuService("/host-monitor/**")
@Slf4j
@AllArgsConstructor
public class HostMonitorRemoteService implements HostMonitorService {

    private final HostMonitorRepository hostMonitorRepository;
    private final HostRepository hostRepository;
    private final HostMonitorDataAdapter hostMonitorDataAdapter;
    private final HostDataAdapter hostDataAdapter;

    @Override
    public PageResult<HostMonitorDTO> pageHostMonitor(HostMonitorPageQuery hostMonitorPageQuery) {
        HostMonitorPageQueryParam hostMonitorPageQueryParam = hostMonitorDataAdapter.convertParam(hostMonitorPageQuery);
        Page<HostMonitor> page = hostMonitorRepository.page(hostMonitorPageQueryParam);
        List<HostMonitor> items = new ArrayList<>(page.getItems());
        List<HostMonitorDTO> hostMonitorDTOList = hostMonitorDataAdapter.sourceToTarget(items);
        List<String> hostIds = items.stream().map(HostMonitor::getHostId).collect(Collectors.toList());
        List<Host> hosts = hostRepository.findByIds(hostIds);
        List<HostDTO> hostDTOS = hostDataAdapter.sourceToTarget(hosts);
        Map<String, HostDTO> hostDTOMap = Maps.uniqueIndex(hostDTOS, HostDTO::getHostId);
        for (HostMonitorDTO hostMonitorDTO : hostMonitorDTOList) {
            HostDTO hostDTO = hostDTOMap.get(hostMonitorDTO.getHostId());
            hostMonitorDTO.setHost(hostDTO);
        }
        return PageResult.succeed(PageUtils.build(page, hostMonitorDTOList));
    }

    @Override
    public PojoResult<HostMonitorDTO> findByHostId(String hostId) {
        Host host = hostRepository.find(new HostId(hostId));
        HostDTO hostDTO = hostDataAdapter.sourceToTarget(host);

        HostMonitor monitor = hostMonitorRepository.findByHostId(hostId);
        if (monitor == null) {
            // 不存在则插入
            monitor = new HostMonitor();
            monitor.setHostId(hostId);
            monitor.setMonitorStatus(MonitorStatus.NOT_START.getStatus());
            monitor.setMonitorUrl(Strings.format(MonitorConst.DEFAULT_URL_FORMAT, host.getServerAddr()));
            monitor.setAccessToken(MonitorConst.DEFAULT_ACCESS_TOKEN);
            monitor.setIsDeleted(DeleteStatusEnum.NO.getValue());
            monitor.setAccessToken(MonitorConst.DEFAULT_ACCESS_TOKEN);
            hostMonitorRepository.save(monitor);
        }
        HostMonitorDTO hostMonitorDTO = hostMonitorDataAdapter.sourceToTarget(monitor);
        hostMonitorDTO.setHost(hostDTO);
        return PojoResult.succeed(hostMonitorDTO);
    }

    @Override
    public PojoResult<Boolean> installAgent(InstallMonitorAgentCommand command) {
        Host host = hostRepository.find(new HostId(command.getHostId()));
        HostDTO hostDTO = hostDataAdapter.sourceToTarget(host);
        HostMonitor monitor = hostMonitorRepository.findByHostId(command.getHostId());
        if (!Strings.eq(monitor.getMonitorStatus(), MonitorStatus.STARTING.getStatus())) {
            throw new BusinessException(DevOpsErrorMessage.AGENT_STATUS_RUNNING);
        }
        return null;
    }

    @Override
    public PojoResult<Boolean> syncAgent(SyncMonitorAgentCommand command) {

        return null;
    }

    @Override
    public PojoResult<Boolean> updateMonitorConfig(UpdateMonitorAgentCommand command) {
        return null;
    }
}
