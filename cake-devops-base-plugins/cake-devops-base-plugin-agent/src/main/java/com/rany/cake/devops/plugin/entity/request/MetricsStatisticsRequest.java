package com.rany.cake.devops.plugin.entity.request;

import com.rany.cake.devops.plugin.constant.GranularityType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 监控请求
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/7/4 17:20
 */
@Data
@ApiModel(value = "监控统计请求")
public class MetricsStatisticsRequest {

    /**
     * @see GranularityType
     */
    @ApiModelProperty(value = "数据粒度")
    private Integer granularity;

    @ApiModelProperty(value = "开始区间 (秒)")
    private Long startRange;

    @ApiModelProperty(value = "结束区间 (秒)")
    private Long endRange;

    @ApiModelProperty(value = "磁盘序列")
    private String seq;

}
