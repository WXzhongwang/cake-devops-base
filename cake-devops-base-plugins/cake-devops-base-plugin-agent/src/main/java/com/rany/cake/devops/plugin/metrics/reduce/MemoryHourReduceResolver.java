package com.rany.cake.devops.plugin.metrics.reduce;

import com.alibaba.fastjson.JSON;
import com.rany.cake.devops.plugin.entity.bo.MemoryUsageBO;
import com.rany.cake.devops.plugin.utils.PathBuilders;
import com.rany.cake.devops.plugin.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 内存时级数据规约器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/7/3 22:45
 */
@Slf4j
@Component
public class MemoryHourReduceResolver extends BaseMetricsHourReduceResolver<MemoryUsageBO> {

    public MemoryHourReduceResolver() {
        super((prevHour) -> PathBuilders.getMemoryMonthDataPath(Utils.getRangeStartMonth(prevHour)));
    }

    @Override
    protected MemoryUsageBO computeHourReduceData(String currentHour, String prevHour) {
        // 设置规约数据
        MemoryUsageBO reduce = new MemoryUsageBO();
        reduce.setUr(this.getAvgReduceData(MemoryUsageBO::getUr, 3));
        reduce.setUs(this.getAvgReduceData(MemoryUsageBO::getUs));
        log.debug("内存时级数据指标 {}", JSON.toJSONString(reduce));
        return reduce;
    }

}
