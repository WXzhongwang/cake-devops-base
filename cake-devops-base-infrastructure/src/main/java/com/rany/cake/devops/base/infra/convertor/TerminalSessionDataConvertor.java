package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.aggregate.TerminalSession;
import com.rany.cake.devops.base.infra.po.TerminalSessionPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 会话终端
 *
 * @author zhongshengwang
 * @description 会话终端
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface TerminalSessionDataConvertor extends BaseConvertor<TerminalSession, TerminalSessionPO> {


    /**
     * 聚合根转PO
     *
     * @param terminalSession 聚合根
     * @return PO
     */
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "sessionType", target = "sessionType")
    @Mapping(source = "sessionClosed", target = "sessionClosed")
    @Override
    TerminalSessionPO sourceToTarget(TerminalSession terminalSession);

    /**
     * PO转聚合根
     *
     * @param terminalSessionPO PO
     * @return 聚合根
     */

    @Override
    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "sessionType", source = "sessionType")
    @Mapping(target = "sessionClosed", source = "sessionClosed")
    TerminalSession targetToSource(TerminalSessionPO terminalSessionPO);
}
