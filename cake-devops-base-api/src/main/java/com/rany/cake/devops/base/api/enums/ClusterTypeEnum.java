package com.rany.cake.devops.base.api.enums;

import lombok.Getter;

/**
 * 集群类型
 *
 * @author zhongshengwang
 * @description 应用开发环境
 * @date 2023/1/15 15:47
 * @email 18668485565163.com
 */
@Getter
public enum ClusterTypeEnum {

    K8S(1, "k8s"),
    ALIYUN(2, "阿里云容器服务"),
    TENCENT(3, "腾讯云容器服务"),
    ;

    private Integer code;

    private String value;

    ClusterTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
