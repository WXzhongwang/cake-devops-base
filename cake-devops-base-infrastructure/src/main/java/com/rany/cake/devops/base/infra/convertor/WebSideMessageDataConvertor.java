package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.entity.WebSideMessage;
import com.rany.cake.devops.base.infra.po.WebSideMessagePO;
import org.mapstruct.Mapper;

/**
 * 站内信
 *
 * @author zhongshengwang
 * @description 站内信
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface WebSideMessageDataConvertor extends BaseConvertor<WebSideMessage, WebSideMessagePO> {


}
