package com.rany.cake.devops.base.domain.pk;

import com.cake.framework.common.base.Identifier;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * ReleaseId
 *
 * @author zhongshengwang
 * @description 每一次发布ID
 * @date 2023/1/12 22:49
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
public class ReleaseId implements Identifier {
    String releaseId;

    public ReleaseId(String releaseId) {
        if (StringUtils.isEmpty(releaseId)) {
            throw new IllegalArgumentException();
        }
        this.releaseId = releaseId;
    }
}
