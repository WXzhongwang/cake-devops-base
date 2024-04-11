package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.ServerKeyDTO;
import com.rany.cake.devops.base.api.query.key.ServerKeyPageQuery;
import com.rany.cake.devops.base.domain.entity.ServerKey;
import com.rany.cake.devops.base.domain.repository.param.ServerKeyQueryParam;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;

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
    ServerKeyDTO sourceToTarget(ServerKey serverKey);

    /**
     * PO转聚合根
     *
     * @param serverKeyDTO PO
     * @return 聚合根
     */
    ServerKey targetToSource(ServerKeyDTO serverKeyDTO);


    ServerKeyQueryParam convertParam(ServerKeyPageQuery appPageQuery);
}
