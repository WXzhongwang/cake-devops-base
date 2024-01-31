package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.ServerKeyDTO;
import com.rany.cake.devops.base.api.query.ServerKeyPageQuery;
import com.rany.cake.devops.base.domain.aggregate.ServerKey;
import com.rany.cake.devops.base.domain.repository.param.ServerKeyQueryParam;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 主机秘钥
 *
 * @author zhongshengwang
 * @description 主机秘钥
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface ServerKeyDataAdapter extends BaseConvertor<ServerKey, ServerKeyDTO> {


    /**
     * 聚合根转PO
     *
     * @param serverKey 聚合根
     * @return PO
     */
    @Mapping(source = "serverKeyId.serverKeyId", target = "serverKeyId")
    ServerKeyDTO sourceToTarget(ServerKey serverKey);

    /**
     * PO转聚合根
     *
     * @param serverKeyDTO PO
     * @return 聚合根
     */

    @Mapping(target = "serverKeyId.serverKeyId", source = "serverKeyId")
    ServerKey targetToSource(ServerKeyDTO serverKeyDTO);


    ServerKeyQueryParam convertParam(ServerKeyPageQuery appPageQuery);
}
