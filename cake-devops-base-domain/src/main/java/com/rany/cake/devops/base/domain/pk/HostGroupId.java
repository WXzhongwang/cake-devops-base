package com.rany.cake.devops.base.domain.pk;

import com.cake.framework.common.base.Identifier;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 主机分组ID
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/12 22:49
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
public class HostGroupId implements Identifier {
    String hostGroupId;

    public HostGroupId(String hostGroupId) {
        if (StringUtils.isEmpty(hostGroupId)) {
            throw new IllegalArgumentException();
        }
        this.hostGroupId = hostGroupId;
    }
}
