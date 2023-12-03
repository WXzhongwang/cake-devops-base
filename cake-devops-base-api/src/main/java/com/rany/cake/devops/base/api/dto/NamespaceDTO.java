package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class NamespaceDTO extends DTO {
    private Long id;
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

    private String status;


    private String isDeleted;
    private Date gmtCreate;
    private Date gmtModified;
    private String creator;
    private String modifier;
}
