package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.entity.ServerProxy;
import com.rany.cake.devops.base.infra.po.ServerProxyPO;
import org.mapstruct.Mapper;

/**
 * 服务器账号
 *
 * @author zhongshengwang
 * @description 服务器账号
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface ServerProxyDataConvertor extends BaseConvertor<ServerProxy, ServerProxyPO> {


    /**
     * 聚合根转PO
     *
     * @param serverKey 聚合根
     * @return PO
     */
    @Override
    ServerProxyPO sourceToTarget(ServerProxy serverKey);

    /**
     * PO转聚合根
     *
     * @param serverKeyPO PO
     * @return 聚合根
     */

    @Override
    ServerProxy targetToSource(ServerProxyPO serverKeyPO);
}
