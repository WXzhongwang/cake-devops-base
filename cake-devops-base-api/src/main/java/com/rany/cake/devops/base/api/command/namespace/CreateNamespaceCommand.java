package com.rany.cake.devops.base.api.command.namespace;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建命名空间
 *
 * @author zhongshengwang
 * @description 创建命名空间
 * @date 2023/1/28 20:10
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateNamespaceCommand extends DTO {

    private Long clusterId;

    private String name;
    /**
     * 所有容器的 CPU 请求总额不得超过 x CPU。
     */
    private Double requestCpu;
    /**
     * 所有容器的内存请求总额不得超过 x GiB。
     */
    private Double requestMemory;
    /**
     * 所有容器的 CPU 限额总额不得超过 x CPU。
     */
    private Double limitCpu;
    /**
     * 所有容器的内存限额总额不得超过 x GiB。
     */
    private Double limitMemory;

    /**
     * 最多创建x个Pods
     */
    private Integer maxPods;
    /**
     * 最多使用 x GPU
     */
    private Integer maxGpu;
    /**
     * 最多创建x个services
     */
    private Integer maxServices;

}
