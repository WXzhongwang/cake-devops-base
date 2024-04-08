package com.rany.cake.devops.base.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rany.cake.devops.base.service.handler.host.HostMonitorEndpointService;
import com.rany.cake.devops.base.service.handler.host.MachineMonitorEndpointRequest;
import com.rany.cake.toolkit.lang.utils.Valid;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/devops/host-monitor/endpoint")
public class HostMonitorEndpointController {

    @Resource
    private HostMonitorEndpointService hostMonitorEndpointService;

    /**
     * ping
     *
     * @param hostId
     * @return
     */
    @GetMapping("/ping")
    public Integer sendPing(@RequestParam("hostId") String hostId) {
        return hostMonitorEndpointService.ping(hostId);
    }

    @GetMapping("/metrics")
    @ApiOperation(value = "获取机器基本指标")
    public JSONObject getBaseMetrics(@RequestParam("hostId") String hostId) {
        return hostMonitorEndpointService.getBaseMetrics(hostId);
    }

    @GetMapping("/load")
    @ApiOperation(value = "获取系统负载")
    public JSONObject getSystemLoad(@RequestParam("hostId") String hostId) {
        return hostMonitorEndpointService.getSystemLoad(hostId);
    }

    @GetMapping("/top")
    @ApiOperation(value = "获取top进程")
    public JSONArray getTopProcesses(@RequestParam("hostId") String hostId, String name) {
        return hostMonitorEndpointService.getTopProcesses(hostId, name);
    }

    @GetMapping("/disk-name")
    @ApiOperation(value = "获取磁盘名称")
    public JSONArray getCpuStatistics(@RequestParam("hostId") String hostId) {
        return hostMonitorEndpointService.getDiskName(hostId);
    }

    @PostMapping("/chart-cpu")
    @ApiOperation(value = "获取cpu图表")
    public JSONObject getCpuStatistics(@RequestBody MachineMonitorEndpointRequest request) {
        this.validChartParams(request);
        return hostMonitorEndpointService.getCpuChart(request);
    }

    @PostMapping("/chart-memory")
    @ApiOperation(value = "获取内存图表")
    public JSONObject getMemoryStatistics(@RequestBody MachineMonitorEndpointRequest request) {
        this.validChartParams(request);
        return hostMonitorEndpointService.getMemoryChart(request);
    }

    @PostMapping("/chart-net")
    @ApiOperation(value = "获取网络图表")
    public JSONObject getNetStatistics(@RequestBody MachineMonitorEndpointRequest request) {
        this.validChartParams(request);
        return hostMonitorEndpointService.getNetChart(request);
    }

    @PostMapping("/chart-disk")
    @ApiOperation(value = "获取磁盘图表")
    public JSONObject getDiskStatistics(@RequestBody MachineMonitorEndpointRequest request) {
        this.validChartParams(request);
        return hostMonitorEndpointService.getDiskChart(request);
    }

    private void validChartParams(MachineMonitorEndpointRequest request) {
        Valid.notNull(request.getHostId());
        Valid.notNull(request.getGranularity());
        Valid.notNull(request.getStartRange());
        Valid.notNull(request.getEndRange());
    }
}
