package com.rany.cake.devops.plugin.controller;

import com.rany.cake.devops.plugin.entity.dto.DiskIoUsageDTO;
import com.rany.cake.devops.plugin.entity.dto.DiskStoreUsageDTO;
import com.rany.cake.devops.plugin.entity.vo.*;
import com.rany.cake.devops.plugin.metrics.MetricsProvider;
import com.rany.cake.toolkit.lang.convert.Converts;
import com.rany.cake.toolkit.lang.wrapper.HttpWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 机器指标 api
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/6/29 16:23
 */
@Api(tags = "机器指标")
@RestController
@RequestMapping("/cake/machine-monitor-agent/api/metrics")
public class MachineMetricsController {

    @Resource
    private MetricsProvider metricsProvider;

    @GetMapping("/base")
    @ApiOperation(value = "获取机器基本指标")
    public HttpWrapper<BaseMetricsVO> getBaseMetrics(@RequestParam("limit") Integer limit) {
        // 查询基本数据指标
        BaseMetricsVO base = new BaseMetricsVO();
        base.setOs(Converts.to(metricsProvider.getOsInfo(), OsInfoVO.class));
        base.setLoad(Converts.to(metricsProvider.getSystemLoad(), SystemLoadVO.class));
        base.setDisks(Converts.toList(metricsProvider.getDiskStoreUsage(), DiskStoreUsageVO.class));
        base.setProcesses(Converts.toList(metricsProvider.getProcesses(null, limit), SystemProcessVO.class));
        return HttpWrapper.ok(base);
    }

    @GetMapping("/info")
    @ApiOperation(value = "获取机器信息")
    public HttpWrapper<OsInfoVO> getOsInfo() {
        return HttpWrapper.ok(Converts.to(metricsProvider.getOsInfo(), OsInfoVO.class));
    }

    @GetMapping("/system-load")
    @ApiOperation(value = "获取系统负载")
    public HttpWrapper<SystemLoadVO> getSystemLoad() {
        return HttpWrapper.ok(Converts.to(metricsProvider.getSystemLoad(), SystemLoadVO.class));
    }

    @GetMapping("/cpu-usage")
    @ApiOperation(value = "获取cpu使用信息")
    public HttpWrapper<CpuUsageVO> getCpuUsage() {
        return HttpWrapper.ok(Converts.to(metricsProvider.getCpuUsage(), CpuUsageVO.class));
    }

    @GetMapping("/memory-usage")
    @ApiOperation(value = "获取内存使用信息")
    public HttpWrapper<MemoryUsageVO> getMemoryUsage() {
        return HttpWrapper.ok(Converts.to(metricsProvider.getMemoryUsage(), MemoryUsageVO.class));
    }

    @GetMapping("/net-bandwidth")
    @ApiOperation(value = "获取网络带宽使用信息")
    public HttpWrapper<NetBandwidthVO> getNetBandwidth() {
        return HttpWrapper.ok(Converts.to(metricsProvider.getNetBandwidth(), NetBandwidthVO.class));
    }

    @GetMapping("/disk-store-usage")
    @ApiOperation(value = "获取硬盘空间使用信息")
    public HttpWrapper<List<DiskStoreUsageVO>> getDiskStoreUsage() {
        return HttpWrapper.ok(Converts.toList(metricsProvider.getDiskStoreUsage(), DiskStoreUsageVO.class));
    }

    @GetMapping("/disk-store-usage-merge")
    @ApiOperation(value = "合并获取硬盘空间使用信息")
    public HttpWrapper<DiskStoreUsageVO> getDiskStoreUsageMerge() {
        List<DiskStoreUsageDTO> store = metricsProvider.getDiskStoreUsage();
        if (store.size() == 1) {
            return HttpWrapper.ok(Converts.to(store.get(0), DiskStoreUsageVO.class));
        }
        // 合并
        long totalSpace = store.stream().mapToLong(DiskStoreUsageDTO::getTotalSpace).sum();
        long freeSpace = store.stream().mapToLong(DiskStoreUsageDTO::getFreeSpace).sum();
        long usageSpace = totalSpace - freeSpace;
        DiskStoreUsageDTO merge = new DiskStoreUsageDTO();
        merge.setTotalSpace(totalSpace);
        merge.setUsageSpace(usageSpace);
        merge.setFreeSpace(freeSpace);
        merge.setUsage((double) usageSpace / (double) totalSpace);
        return HttpWrapper.ok(Converts.to(merge, DiskStoreUsageVO.class));
    }

    @GetMapping("/disk-name")
    @ApiOperation(value = "获取硬盘名称")
    public HttpWrapper<List<DiskNameVO>> getDiskName() {
        return HttpWrapper.ok(metricsProvider.getDiskName());
    }

    @GetMapping("/disk-io-usage")
    @ApiOperation(value = "获取硬盘io使用信息")
    public HttpWrapper<List<DiskIoUsageVO>> getDiskIoUsage() {
        return HttpWrapper.ok(Converts.toList(metricsProvider.getDiskIoUsage(), DiskIoUsageVO.class));
    }

    @GetMapping("/disk-io-usage-merge")
    @ApiOperation(value = "合并获取硬盘io使用信息")
    public HttpWrapper<DiskIoUsageVO> getDiskIoUsageMerge() {
        List<DiskIoUsageDTO> io = metricsProvider.getDiskIoUsage();
        if (io.size() == 1) {
            return HttpWrapper.ok(Converts.to(io.get(0), DiskIoUsageVO.class));
        }
        // 合并
        long readCount = io.stream().mapToLong(DiskIoUsageDTO::getReadCount).sum();
        long readBytes = io.stream().mapToLong(DiskIoUsageDTO::getReadBytes).sum();
        long writeCount = io.stream().mapToLong(DiskIoUsageDTO::getWriteCount).sum();
        long writeBytes = io.stream().mapToLong(DiskIoUsageDTO::getWriteBytes).sum();
        long usageTime = io.stream().mapToLong(DiskIoUsageDTO::getUsageTime).sum();
        DiskIoUsageDTO merge = new DiskIoUsageDTO();
        merge.setReadCount(readCount);
        merge.setReadBytes(readBytes);
        merge.setWriteCount(writeCount);
        merge.setWriteBytes(writeBytes);
        merge.setUsageTime(usageTime);
        return HttpWrapper.ok(Converts.to(merge, DiskIoUsageVO.class));
    }

    @GetMapping("/top-processes")
    @ApiOperation(value = "获取top进程")
    public HttpWrapper<List<SystemProcessVO>> getTopProgress(@RequestParam(value = "name", required = false) String name,
                                                             @RequestParam("limit") Integer limit) {
        return HttpWrapper.ok(Converts.toList(metricsProvider.getProcesses(name, limit), SystemProcessVO.class));
    }

}
