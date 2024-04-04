package com.rany.cake.devops.base.domain.repository.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServerProxyQueryParam extends BasePageParam {
    private String proxyHost;
    private String proxyType;
    private String proxyUsername;
}
