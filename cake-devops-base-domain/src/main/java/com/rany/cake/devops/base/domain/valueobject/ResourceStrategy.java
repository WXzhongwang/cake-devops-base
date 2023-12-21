package com.rany.cake.devops.base.domain.valueobject;

import com.cake.framework.common.base.BaseValueObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 副本资源略
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 16:58
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceStrategy extends BaseValueObject {

    /**
     * 副本数
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

    public ResourceStrategy() {
    }

    public ResourceStrategy(Integer replicas, String cpu, String memory) {
        this.replicas = replicas;
        this.cpu = cpu;
        this.memory = memory;
    }
}
