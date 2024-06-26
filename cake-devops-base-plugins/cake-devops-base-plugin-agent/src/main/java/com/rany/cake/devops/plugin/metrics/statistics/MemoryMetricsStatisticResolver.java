package com.rany.cake.devops.plugin.metrics.statistics;

import com.rany.cake.devops.plugin.constant.Const;
import com.rany.cake.devops.plugin.constant.DataMetricsType;
import com.rany.cake.devops.plugin.entity.bo.MemoryUsageBO;
import com.rany.cake.devops.plugin.entity.request.MetricsStatisticsRequest;
import com.rany.cake.devops.plugin.entity.vo.MemoryMetricsStatisticsVO;
import com.rany.cake.devops.plugin.entity.vo.MetricsStatisticsVO;
import com.rany.cake.devops.plugin.utils.Formats;
import com.rany.cake.devops.plugin.utils.Utils;
import com.rany.cake.toolkit.lang.wrapper.TimestampValue;

import java.util.List;
import java.util.stream.DoubleStream;

/**
 * 内存数据指标统计
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/7/5 14:24
 */
public class MemoryMetricsStatisticResolver extends BaseMetricsStatisticResolver<MemoryUsageBO, MemoryMetricsStatisticsVO> {

    /**
     * 使用量
     */
    private final MetricsStatisticsVO<Double> size;

    /**
     * 使用率
     */
    private final MetricsStatisticsVO<Double> usage;

    public MemoryMetricsStatisticResolver(MetricsStatisticsRequest request) {
        super(request, DataMetricsType.MEMORY, new MemoryMetricsStatisticsVO());
        this.size = new MetricsStatisticsVO<>();
        this.usage = new MetricsStatisticsVO<>();
        metrics.setSize(size);
        metrics.setUsage(usage);
    }

    @Override
    protected void computeMetricsData(List<MemoryUsageBO> rows, Long start, Long end) {
        double avgSize = Utils.getLongStream(rows, MemoryUsageBO::getUs)
                .average()
                .orElse(Const.D_0);
        double avgUsage = Utils.getDoubleStream(rows, MemoryUsageBO::getUr)
                .average()
                .orElse(Const.D_0);
        size.getMetrics().add(new TimestampValue<>(start, Formats.roundToDouble(avgSize, 3)));
        usage.getMetrics().add(new TimestampValue<>(start, Formats.roundToDouble(avgUsage, 3)));
    }

    @Override
    protected void computeMetricsMax() {
        double sizeMax = super.calcDataReduce(size.getMetrics(), DoubleStream::max);
        double usageMax = super.calcDataReduce(usage.getMetrics(), DoubleStream::max);
        size.setMax(sizeMax);
        usage.setMax(usageMax);
    }

    @Override
    protected void computeMetricsMin() {
        double sizeMin = super.calcDataReduce(size.getMetrics(), DoubleStream::min);
        double usageMin = super.calcDataReduce(usage.getMetrics(), DoubleStream::min);
        size.setMin(sizeMin);
        usage.setMin(usageMin);
    }

    @Override
    protected void computeMetricsAvg() {
        double sizeAvg = super.calcDataAvg(size.getMetrics());
        double usageAvg = super.calcDataAvg(usage.getMetrics());
        size.setAvg(sizeAvg);
        usage.setAvg(usageAvg);
    }

}
