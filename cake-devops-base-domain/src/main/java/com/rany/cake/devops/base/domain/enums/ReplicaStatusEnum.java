package com.rany.cake.devops.base.domain.enums;

import lombok.Getter;

/**
 * 副本状态
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/19 17:42
 * @email 18668485565163.com
 */
@Getter
public enum ReplicaStatusEnum {

    PENDING(1, "启动中"),
    RUNNING(2, "成功"),
    FAILED(3, "失败"),
    DESTROYING(4, "销毁中"),
    ;

    private Integer code;

    private String value;

    ReplicaStatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
