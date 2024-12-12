package com.rany.cake.devops.base.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 告警组通知方式
 */
@Getter
@AllArgsConstructor
public enum AlarmGroupNotifyType {

    /**
     * webhook 通知
     */
    WEBHOOK(10),

    ;

    private final Integer type;

    public static AlarmGroupNotifyType of(Integer type) {
        if (type == null) {
            return null;
        }
        for (AlarmGroupNotifyType value : values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        return null;
    }

}
