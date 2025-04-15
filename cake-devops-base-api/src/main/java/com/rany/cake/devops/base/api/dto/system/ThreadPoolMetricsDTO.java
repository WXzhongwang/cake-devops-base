package com.rany.cake.devops.base.api.dto.system;

import com.rany.cake.devops.base.api.common.base.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 线程池指标响应
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/3/23 18:17
 */
@Data
@ApiModel(value = "线程池指标响应")
@EqualsAndHashCode(callSuper = false)
public class ThreadPoolMetricsDTO extends DTO {
    
    @ApiModelProperty(value = "type")
    private Integer type;

    @ApiModelProperty(value = "活跃线程数")
    private Integer activeThreadCount;

    @ApiModelProperty(value = "总任务数")
    private Long totalTaskCount;

    @ApiModelProperty(value = "已完成任务数")
    private Long completedTaskCount;

    @ApiModelProperty(value = "队列任务数")
    private Integer queueSize;

}
