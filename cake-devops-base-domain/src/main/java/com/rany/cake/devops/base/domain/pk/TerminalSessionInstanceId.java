package com.rany.cake.devops.base.domain.pk;

import com.cake.framework.common.base.Identifier;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TerminalSessionInstanceId
 *
 * @author zhongshengwang
 * @description TerminalSessionId
 * @date 2023/1/12 22:49
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
public class TerminalSessionInstanceId implements Identifier {
    Long id;

    public TerminalSessionInstanceId(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }
}
