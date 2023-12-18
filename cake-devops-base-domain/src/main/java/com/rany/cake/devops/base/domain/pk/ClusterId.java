package com.rany.cake.devops.base.domain.pk;

import com.cake.framework.common.base.Identifier;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 16:20
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
public class ClusterId implements Identifier {

    String clusterId;

    public ClusterId(String clusterId) {
        if (StringUtils.isEmpty(clusterId)) {
            throw new IllegalArgumentException();
        }
        this.clusterId = clusterId;
    }
}
