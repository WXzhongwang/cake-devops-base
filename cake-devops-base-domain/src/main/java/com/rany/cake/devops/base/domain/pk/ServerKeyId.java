package com.rany.cake.devops.base.domain.pk;

import com.cake.framework.common.base.Identifier;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 秘钥ID
 *
 * @author zhongshengwang
 * @description 秘钥ID
 * @date 2023/1/12 22:49
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
public class ServerKeyId implements Identifier {
    String serverKeyId;

    public ServerKeyId(String serverKeyId) {
        if (StringUtils.isEmpty(serverKeyId)) {
            throw new IllegalArgumentException();
        }
        this.serverKeyId = serverKeyId;
    }
}
