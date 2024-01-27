package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.ServerAccountDTO;
import com.rany.cake.devops.base.api.query.ServerAccountPageQuery;
import com.rany.cake.devops.base.domain.aggregate.ServerAccount;
import com.rany.cake.devops.base.domain.repository.param.ServerAccountQueryParam;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 主机账号
 *
 * @author zhongshengwang
 * @description 主机账号
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface ServerAccountDataAdapter extends BaseConvertor<ServerAccount, ServerAccountDTO> {


    /**
     * 聚合根转PO
     *
     * @param serverAccount 聚合根
     * @return PO
     */
    @Mapping(source = "serverAccountId.serverAccountId", target = "serverAccountId")
    @Mapping(source = "hostId.hostId", target = "hostId")
    ServerAccountDTO sourceToTarget(ServerAccount serverAccount);

    /**
     * PO转聚合根
     *
     * @param serverAccountDTO PO
     * @return 聚合根
     */

    @Mapping(target = "serverAccountId.serverAccountId", source = "serverAccountId")
    @Mapping(target = "hostId.hostId", source = "hostId")
    ServerAccount targetToSource(ServerAccountDTO serverAccountDTO);


    ServerAccountQueryParam convertParam(ServerAccountPageQuery appPageQuery);
}
