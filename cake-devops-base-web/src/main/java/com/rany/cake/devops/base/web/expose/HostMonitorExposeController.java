package com.rany.cake.devops.base.web.expose;

import com.rany.cake.devops.base.domain.entity.HostMonitor;
import com.rany.cake.devops.base.domain.repository.HostMonitorRepository;
import com.rany.cake.devops.base.util.enums.MonitorStatus;
import com.rany.cake.toolkit.lang.wrapper.HttpWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 机器监控 暴露api
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/9/1 17:52
 */
@Api(tags = "暴露服务-机器监控")
@RestController
@RequestMapping("/cake/expose-api/machine-monitor")
public class HostMonitorExposeController {
    @Resource
    private HostMonitorRepository hostMonitorRepository;

    @GetMapping("/started")
    @ApiOperation(value = "监控启动回调")
    public HttpWrapper<Void> started(@RequestParam("hostId") String hostId, @RequestParam("version") String version) {
        HostMonitor monitor = hostMonitorRepository.findByHostId(hostId);
        monitor.setMonitorStatus(MonitorStatus.RUNNING.getStatus());
        monitor.setAgentVersion(version);
        hostMonitorRepository.update(monitor);
        return HttpWrapper.ok();
    }

}
