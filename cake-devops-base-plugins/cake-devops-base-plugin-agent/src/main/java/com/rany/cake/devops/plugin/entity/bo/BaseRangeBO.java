package com.rany.cake.devops.plugin.entity.bo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 时间区间
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/7/1 11:23
 */
@Data
@ApiModel(value = "时间区间")
public class BaseRangeBO {

    @JSONField(ordinal = 1)
    @ApiModelProperty(value = "开始时间 (秒) startRange")
    private Long sr;

    @JSONField(ordinal = 2)
    @ApiModelProperty(value = "结束时间 (秒) endRange")
    private Long er;

}
