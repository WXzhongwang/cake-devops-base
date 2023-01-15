package com.rany.cake.devops.base.domain.type;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 16:23
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
public class ClusterName {
    String name;

    public ClusterName(String name) {
        if (name == null || name.length() < 8) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }
}
