package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.aggregate.ServerAccount;
import com.rany.cake.devops.base.infra.po.ServerAccountPO;
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
public interface ServerAccountDataConvertor extends BaseConvertor<ServerAccount, ServerAccountPO> {


    /**
     * 聚合根转PO
     *
     * @param serverAccount 聚合根
     * @return PO
     */
    @Mapping(source = "serverAccountId.serverAccountId", target = "serverAccountId")
    @Mapping(source = "hostId.hostId", target = "hostId")
    @Override
    ServerAccountPO sourceToTarget(ServerAccount serverAccount);

    /**
     * PO转聚合根
     *
     * @param serverAccountPO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "serverAccountId.serverAccountId", source = "serverAccountId")
    @Mapping(target = "hostId.hostId", source = "hostId")
    ServerAccount targetToSource(ServerAccountPO serverAccountPO);
}
