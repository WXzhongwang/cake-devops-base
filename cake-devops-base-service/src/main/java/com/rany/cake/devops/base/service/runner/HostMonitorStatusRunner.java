package com.rany.cake.devops.base.service.runner;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.base.MonitorConst;
import com.rany.cake.devops.base.domain.entity.HostMonitor;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostMonitorRepository;
import com.rany.cake.devops.base.domain.repository.HostRepository;
import com.rany.cake.devops.base.domain.repository.param.HostMonitorPageQueryParam;
import com.rany.cake.devops.base.service.handler.agent.MonitorAgents;
import com.rany.cake.devops.base.util.enums.MonitorStatus;
import com.rany.cake.toolkit.lang.Threads;
import com.rany.cake.toolkit.lang.utils.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 机器监控插件状态 runner
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/18 18:23
 */
@Component
@Order(4000)
@Slf4j
public class HostMonitorStatusRunner implements CommandLineRunner {

    @Resource
    private HostMonitorRepository hostMonitorRepository;
    @Resource
    private HostRepository hostRepository;
    @Resource
    private MonitorAgents monitorAgents;

    @Override
    public void run(String... args) throws Exception {
        log.info("重置机器监控插件状态-开始");
        // 清除启动中的状态
        this.clearStartingStatus();
        // 异步检查插件状态及版本
        Threads.start(this::checkMonitorStatus);
        log.info("重置机器监控插件状态-结束");
    }

    /**
     * 清除启动中的状态
     */
    private void clearStartingStatus() {
        hostMonitorRepository.clearStartingStatus();
    }

    /**
     * 检查插件状态及版本
     */
    private void checkMonitorStatus() {

        HostMonitorPageQueryParam queryParam = new HostMonitorPageQueryParam();
        Page<HostMonitor> page = hostMonitorRepository.page(queryParam);
        while (page != null && page.getItems().size() == 10) {
            Collection<HostMonitor> items = page.getItems();
            for (HostMonitor monitor : items) {
                log.info("检测机器监控插件状态-开始 {} ({})", monitor.getHostId(), monitor.getMonitorStatus());
                String monitorUrl = monitor.getMonitorUrl();
                String accessToken = monitor.getAccessToken();
                Host host = hostRepository.find(new HostId(monitor.getHostId()));

                // 不存在则设置默认值
                if (Strings.isBlank(monitorUrl)) {
                    monitorUrl = Strings.format(MonitorConst.DEFAULT_URL_FORMAT, host.getServerAddr());
                    accessToken = MonitorConst.DEFAULT_ACCESS_TOKEN;
                    monitor.setMonitorUrl(monitorUrl);
                    monitor.setAccessToken(accessToken);
                }
                // 同步并且获取插件版本
                String monitorVersion = monitorAgents.syncMonitorAgent(monitor.getHostId(), monitorUrl, accessToken);
                if (monitorVersion == null) {
                    // 未启动
                    monitor.setMonitorStatus(MonitorStatus.NOT_START.getStatus());
                } else {
                    monitor.setAgentVersion(monitorVersion);
                    monitor.setMonitorStatus(MonitorStatus.RUNNING.getStatus());
                }
                log.info("检测机器监控插件状态-完成 {} ({}), {}", host.getHostName(), host.getServerAddr(), monitor.getMonitorStatus());
                hostMonitorRepository.update(monitor);
            }
            queryParam.setPageNo(queryParam.getPageNo() + 1);
        }
    }
}
