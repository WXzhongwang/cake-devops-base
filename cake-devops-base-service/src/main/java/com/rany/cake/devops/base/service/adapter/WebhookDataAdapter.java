package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.WebHookConfigDTO;
import com.rany.cake.devops.base.api.query.webhook.WebhookPageQuery;
import com.rany.cake.devops.base.domain.entity.WebhookConfig;
import com.rany.cake.devops.base.domain.repository.param.WebhookConfigQueryParam;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;

/**
 * webhook
 *
 * @author zhongshengwang
 * @description webhook
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface WebhookDataAdapter extends BaseConvertor<WebhookConfig, WebHookConfigDTO> {

    WebhookConfigQueryParam convertParam(WebhookPageQuery webhookPageQuery);
}
