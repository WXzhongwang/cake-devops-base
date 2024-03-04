package com.rany.cake.devops.base.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * webhook类型
 */
@Getter
@AllArgsConstructor
public enum WebHookType {

    /**
     * webhook 通知
     */
    DINGDING(10),

    ;

    private final Integer type;

    public static WebHookType of(Integer type) {
        if (type == null) {
            return null;
        }
        for (WebHookType value : values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        return null;
    }

}
