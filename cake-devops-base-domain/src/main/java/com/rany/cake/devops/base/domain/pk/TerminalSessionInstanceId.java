package com.rany.cake.devops.base.domain.pk;

import com.cake.framework.common.base.Identifier;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

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
    String id;

    public TerminalSessionInstanceId(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }
}
