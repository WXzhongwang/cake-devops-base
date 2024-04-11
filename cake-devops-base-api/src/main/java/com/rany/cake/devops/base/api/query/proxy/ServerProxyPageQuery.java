package com.rany.cake.devops.base.api.query.proxy;

import com.rany.cake.devops.base.api.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServerProxyPageQuery extends BaseQuery {

    private String proxyHost;
    private String proxyType;
    private String proxyUsername;

}
