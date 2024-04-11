package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.WebSideMessageDTO;
import com.rany.cake.devops.base.api.query.message.WebSideMessagePageQuery;
import com.rany.cake.devops.base.domain.entity.WebSideMessage;
import com.rany.cake.devops.base.domain.repository.param.WebSideMessagePageQueryParam;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;

/**
 * WebSideMessageDataAdapter
 *
 * @author zhongshengwang
 * @description WebSideMessageDataAdapter
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface WebSideMessageDataAdapter extends BaseConvertor<WebSideMessage, WebSideMessageDTO> {

    WebSideMessagePageQueryParam convertParam(WebSideMessagePageQuery webhookPageQuery);
}
