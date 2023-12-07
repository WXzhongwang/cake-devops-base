package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.aggregate.TerminalSessionInstance;
import com.rany.cake.devops.base.infra.po.TerminalSessionInstancePO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 会话终端实例
 *
 * @author zhongshengwang
 * @description 会话终端实例
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface TerminalSessionInstanceDataConvertor extends BaseConvertor<TerminalSessionInstance, TerminalSessionInstancePO> {


    /**
     * 聚合根转PO
     *
     * @param terminalSessionInstance 聚合根
     * @return PO
     */
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "sessionId.id", target = "sessionId")
    @Override
    TerminalSessionInstancePO sourceToTarget(TerminalSessionInstance terminalSessionInstance);

    /**
     * PO转聚合根
     *
     * @param terminalSessionInstancePO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "sessionId.id", source = "sessionId")
    TerminalSessionInstance targetToSource(TerminalSessionInstancePO terminalSessionInstancePO);
}
