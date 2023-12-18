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
 * @date 2023/1/12 22:49
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
public class AppId implements Identifier {
    String appId;

    public AppId(String appId) {
        if (StringUtils.isEmpty(appId)) {
            throw new IllegalArgumentException();
        }
        this.appId = appId;
    }
}
