package com.rany.cake.devops.plugin.metrics.statistics;


import com.rany.cake.devops.plugin.constant.DataMetricsType;
import com.rany.cake.devops.plugin.entity.bo.CpuUsageBO;
import com.rany.cake.devops.plugin.entity.request.MetricsStatisticsRequest;
import com.rany.cake.devops.plugin.entity.vo.CpuMetricsStatisticsVO;
import com.rany.cake.devops.plugin.entity.vo.MetricsStatisticsVO;
import com.rany.cake.devops.plugin.utils.Formats;
import com.rany.cake.devops.plugin.utils.Utils;
import com.rany.cake.toolkit.lang.constant.Const;
import com.rany.cake.toolkit.lang.wrapper.TimestampValue;

import java.util.List;
import java.util.stream.DoubleStream;

/**
 * 处理器数据指标统计
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/7/4 17:51
 */
public class CpuMetricsStatisticResolver extends BaseMetricsStatisticResolver<CpuUsageBO, CpuMetricsStatisticsVO> {

    /**
     * 使用率
     */
    private final MetricsStatisticsVO<Double> usage;

    public CpuMetricsStatisticResolver(MetricsStatisticsRequest request) {
        super(request, DataMetricsType.CPU, new CpuMetricsStatisticsVO());
        this.usage = new MetricsStatisticsVO<>();
        metrics.setUsage(usage);
    }

    @Override
    protected void computeMetricsData(List<CpuUsageBO> rows, Long start, Long end) {
        double avgUsage = Utils.getDoubleStream(rows, CpuUsageBO::getU)
                .average()
                .orElse(Const.D_0);
        usage.getMetrics().add(new TimestampValue<>(start, Formats.roundToDouble(avgUsage, 3)));
    }

    @Override
    protected void computeMetricsMax() {
        double max = super.calcDataReduce(usage.getMetrics(), DoubleStream::max);
        usage.setMax(max);
    }

    @Override
    protected void computeMetricsMin() {
        double min = super.calcDataReduce(usage.getMetrics(), DoubleStream::min);
        usage.setMin(min);
    }

    @Override
    protected void computeMetricsAvg() {
        double avg = super.calcDataAvg(usage.getMetrics());
        usage.setAvg(avg);
    }

}
