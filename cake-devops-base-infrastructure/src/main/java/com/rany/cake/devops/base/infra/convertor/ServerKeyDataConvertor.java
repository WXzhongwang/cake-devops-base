package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.entity.ServerKey;
import com.rany.cake.devops.base.infra.po.ServerKeyPO;
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
public interface ServerKeyDataConvertor extends BaseConvertor<ServerKey, ServerKeyPO> {


    /**
     * 聚合根转PO
     *
     * @param serverKey 聚合根
     * @return PO
     */
    @Override
    ServerKeyPO sourceToTarget(ServerKey serverKey);

    /**
     * PO转聚合根
     *
     * @param serverKeyPO PO
     * @return 聚合根
     */

    @Override
    ServerKey targetToSource(ServerKeyPO serverKeyPO);
}
