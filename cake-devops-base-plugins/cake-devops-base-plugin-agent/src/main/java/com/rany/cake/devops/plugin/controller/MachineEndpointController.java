package com.rany.cake.devops.plugin.controller;

import com.rany.cake.devops.plugin.common.http.vo.MachineAlarmConfig;
import com.rany.cake.devops.plugin.constant.Const;
import com.rany.cake.devops.plugin.constant.MachineAlarmType;
import com.rany.cake.devops.plugin.constant.PropertiesConst;
import com.rany.cake.devops.plugin.entity.request.MachineSyncRequest;
import com.rany.cake.devops.plugin.handler.AlarmChecker;
import com.rany.cake.devops.plugin.metrics.collect.MetricsCollectTask;
import com.rany.cake.toolkit.lang.utils.Lists;
import com.rany.cake.toolkit.lang.wrapper.HttpWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 机器监控端点 api
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/6/30 19:00
 */
@Api(tags = "应用端点")
// @RestWrapper
@RestController
@RequestMapping("/cake/machine-monitor-agent/api/endpoint")
public class MachineEndpointController {

    @Resource
    private MetricsCollectTask metricsCollectTask;

    @Resource
    private AlarmChecker alarmChecker;

    @GetMapping("/ping")
    @ApiOperation(value = "检测心跳")
    public HttpWrapper<Integer> ping() {
        return HttpWrapper.ok(Const.ENABLE);
    }

    @GetMapping("/version")
    @ApiOperation(value = "获取版本")
    public HttpWrapper<String> getVersion() {
        return HttpWrapper.ok(PropertiesConst.AGENT_VERSION);
    }

    @GetMapping("/get-machine-id")
    @ApiOperation(value = "获取机器id")
    public HttpWrapper<String> getMachineId() {
        return HttpWrapper.ok(PropertiesConst.HOST_ID);
    }

    @GetMapping("/set-machine-id")
    @ApiOperation(value = "设置机器id")
    public HttpWrapper<String> setMachineId(@RequestParam("hostId") String hostId) {
        String before = PropertiesConst.HOST_ID;
        PropertiesConst.HOST_ID = hostId;
        return HttpWrapper.ok(before);
    }

    @PostMapping("/sync")
    @ApiOperation(value = "同步机器信息")
    public HttpWrapper<String> syncMachineInfo(@RequestBody MachineSyncRequest request) {
        // 设置机器id
        PropertiesConst.HOST_ID = request.getHostId();
        // 设置报警配置
        List<MachineAlarmConfig> config = request.getAlarmConfig();
        if (!Lists.isEmpty(config)) {
            config.forEach(s -> alarmChecker.getAlarmContext().put(MachineAlarmType.of(s.getAlarmType()), s));
        }
        return HttpWrapper.ok(PropertiesConst.AGENT_VERSION);
    }

    @GetMapping("/status")
    @ApiOperation(value = "获取监控启动状态")
    public HttpWrapper<Boolean> getRunStatus() {
        return HttpWrapper.ok(metricsCollectTask.isRun());
    }

    @GetMapping("/start")
    @ApiOperation(value = "开启监控")
    public HttpWrapper<?> startMonitor() {
        metricsCollectTask.setRun(true);
        return HttpWrapper.ok();
    }

    @GetMapping("/stop")
    @ApiOperation(value = "停止监控")
    public HttpWrapper<?> stopMonitor() {
        metricsCollectTask.setRun(false);
        return HttpWrapper.ok();
    }

}
