package com.rany.cake.devops.base.domain.type;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * APP名称
 *
 * @author zhongshengwang
 * @description APP名称
 * @date 2023/1/12 22:55
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
public class AppName {

    String name;

    public AppName(String name) {
        if (name == null || name.length() < 8) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }
}
