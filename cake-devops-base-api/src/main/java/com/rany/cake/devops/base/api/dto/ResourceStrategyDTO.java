package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资源策略
 *
 * @author zhongshengwang
 * @description 资源策略
 * @date 2023/1/28 20:33
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceStrategyDTO extends DTO {

    /**
     * 最大副本数
     */
    private Integer replicas;
    /**
     * cpu资源
     */
    private String cpu;
    /**
     * 内存资源
     */
    private String memory;

    /**
     * cpu资源
     */
    private String maxCpu;
    /**
     * 内存资源
     */
    private String maxMemory;
}
