package com.rany.cake.devops.base.domain.pk;

import com.cake.framework.common.base.Identifier;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 代理ID
 *
 * @author zhongshengwang
 * @description 秘钥ID
 * @date 2023/1/12 22:49
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
public class ServerProxyId implements Identifier {
    String serverProxyId;

    public ServerProxyId(String serverProxyId) {
        if (StringUtils.isEmpty(serverProxyId)) {
            throw new IllegalArgumentException();
        }
        this.serverProxyId = serverProxyId;
    }
}
