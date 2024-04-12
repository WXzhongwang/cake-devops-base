package com.rany.cake.devops.plugin.metrics.collect;

import com.alibaba.fastjson.JSON;
import com.rany.cake.devops.plugin.entity.bo.DiskIoUsageBO;
import com.rany.cake.devops.plugin.metrics.MetricsProvider;
import com.rany.cake.devops.plugin.utils.PathBuilders;
import com.rany.cake.devops.plugin.utils.Utils;
import com.rany.cake.toolkit.lang.constant.Const;
import com.rany.cake.toolkit.lang.time.Dates;
import com.rany.cake.toolkit.lang.utils.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * 硬盘带宽指标收集器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/7/4 13:52
 */
@Slf4j
@Order(530)
@Component
public class DiskMetricsCollector implements IMetricsCollector<DiskIoUsageBO> {

    @Resource
    private MetricsProvider metricsProvider;

    private HardwareAbstractionLayer hardware;

    /**
     * 上次采集硬盘信息
     */
    private List<HWDiskStore> prevDisks;

    /**
     * 上次采集硬盘信息时间
     */
    private long prevTime;

    @PostConstruct
    private void initCollector() {
        log.info("初始化硬盘指标收集器");
        this.hardware = metricsProvider.getHardware();
        this.prevDisks = this.hardware.getDiskStores();
        this.prevTime = System.currentTimeMillis();
    }

    @Override
    public DiskIoUsageBO collect() {
        return Lists.first(this.collectAsList());
    }

    @Override
    public List<DiskIoUsageBO> collectAsList() {
        List<HWDiskStore> prevDisks = this.prevDisks;
        long prevTime = this.prevTime;
        List<HWDiskStore> currentDisks = this.prevDisks = hardware.getDiskStores();
        long currentTime = this.prevTime = System.currentTimeMillis();
        // 计算
        List<DiskIoUsageBO> list = Lists.newList();
        for (int i = 0; i < currentDisks.size(); i++) {
            HWDiskStore currentDisk = currentDisks.get(i);
            HWDiskStore prevDisk = prevDisks.get(i);
            // 设置
            String seq = Utils.getDiskSeq(currentDisk.getModel());
            DiskIoUsageBO disk = new DiskIoUsageBO();
            disk.setSeq(seq);
            disk.setRs((currentDisk.getReadBytes() - prevDisk.getReadBytes()) / Const.BUFFER_KB_1);
            disk.setWs((currentDisk.getWriteBytes() - prevDisk.getWriteBytes()) / Const.BUFFER_KB_1);
            disk.setRc(currentDisk.getReads() - prevDisk.getReads());
            disk.setWc(currentDisk.getWrites() - prevDisk.getWrites());
            disk.setUt(currentDisk.getTransferTime() - prevDisk.getTransferTime());
            disk.setSr(Dates.getSecondTime(prevTime));
            disk.setEr(Dates.getSecondTime(currentTime));
            list.add(disk);
            log.debug("硬盘读写指标-{}: {}", seq, JSON.toJSONString(disk));
            // 拼接到天级数据
            String path = PathBuilders.getDiskDayDataPath(Utils.getRangeStartTime(disk.getSr()), disk.getSeq());
            Utils.appendMetricsData(path, disk);
        }
        return list;
    }

}
