package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.aggregate.ServerKey;
import com.rany.cake.devops.base.infra.po.ServerKeyPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    @Mapping(source = "serverKeyId.serverKeyId", target = "serverKeyId")
    @Override
    ServerKeyPO sourceToTarget(ServerKey serverKey);

    /**
     * PO转聚合根
     *
     * @param serverKeyPO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "serverKeyId.serverKeyId", source = "serverKeyId")
    ServerKey targetToSource(ServerKeyPO serverKeyPO);
}
