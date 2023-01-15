package com.rany.cake.devops.base.domain.pk;

import com.cake.framework.common.base.Identifier;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 16:20
 * @email 18668485565163.com
 */
public class ClusterId implements Identifier {

    Long id;

    public ClusterId(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }
}
