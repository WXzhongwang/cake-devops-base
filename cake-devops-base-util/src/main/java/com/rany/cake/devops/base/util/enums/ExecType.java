package com.rany.cake.devops.base.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 命令执行类型
 *
 * @author zhongshengwang
 * @description EnvDeployStatusEnum
 * @date 2022/12/28 20:15
 * @email 18668485565163.com
 */
@AllArgsConstructor
@Getter
public enum ExecType {

    /**
     * 批量执行
     */
    BATCH_EXEC(10),

    ;

    /**
     * 类型
     */
    private final Integer type;

    public static ExecType of(Integer type) {
        for (ExecType value : values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        return null;
    }

}
