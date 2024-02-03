package com.rany.cake.devops.base.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 代理类型
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/19 17:42
 * @email 18668485565163.com
 */
@AllArgsConstructor
@Getter
public enum ProxyType {

    /**
     * HTTP 代理
     */
    HTTP(1, "http"),

    /**
     * SOCKS4 代理
     */
    SOCKS4(2, "socks4"),

    /**
     * SOCKS5 代理
     */
    SOCKS5(3, "socks5"),

    ;

    private final Integer type;

    private final String label;

    public static ProxyType of(Integer type) {
        if (type == null) {
            return null;
        }
        for (ProxyType value : values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        return null;
    }

    public static ProxyType of(String label) {
        if (label == null) {
            return null;
        }
        for (ProxyType value : values()) {
            if (value.label.equals(label)) {
                return value;
            }
        }
        return null;
    }

}
