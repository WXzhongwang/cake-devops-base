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

    public ResourceStrategy(Integer maxReplicas, Double cpu, Double memory) {
        this.maxReplicas = maxReplicas;
        this.cpu = cpu;
        this.memory = memory;
    }
}
