package com.rany.cake.devops.base.domain.pk;

import com.cake.framework.common.base.Identifier;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 15:55
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
public class MemberId implements Identifier {
    Long id;

    public MemberId(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }
}
