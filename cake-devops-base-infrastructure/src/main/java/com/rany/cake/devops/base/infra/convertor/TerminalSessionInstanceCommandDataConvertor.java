package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.aggregate.TerminalSessionInstanceCommand;
import com.rany.cake.devops.base.infra.po.TerminalSessionInstanceCommandPO;
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
public interface TerminalSessionInstanceCommandDataConvertor extends BaseConvertor<TerminalSessionInstanceCommand, TerminalSessionInstanceCommandPO> {


    /**
     * 聚合根转PO
     *
     * @param terminalSessionInstanceCommand 聚合根
     * @return PO
     */
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "terminalSessionInstanceId.id", target = "terminalSessionInstanceId")
    @Override
    TerminalSessionInstanceCommandPO sourceToTarget(TerminalSessionInstanceCommand terminalSessionInstanceCommand);

    /**
     * PO转聚合根
     *
     * @param terminalSessionInstancePO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "terminalSessionInstanceId.id", source = "terminalSessionInstanceId")
    TerminalSessionInstanceCommand targetToSource(TerminalSessionInstanceCommandPO terminalSessionInstancePO);
}
