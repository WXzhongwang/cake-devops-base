package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/28 20:33
 * @email 18668485565163.com
 */
@Data
public class ResourceStrategyDTO extends DTO {

    /**
     * 最大副本数
     */
    private Integer maxReplicas;
    /**
     * cpu资源
     */
    private Double cpu;
    /**
     * 内存资源
     */
    private Double memory;
}
