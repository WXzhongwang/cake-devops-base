package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.wehook.CreateWebhookCommand;
import com.rany.cake.devops.base.api.command.wehook.DeleteWebhookCommand;
import com.rany.cake.devops.base.api.command.wehook.ModifyWebHookCommand;
import com.rany.cake.devops.base.api.dto.WebHookConfigDTO;
import com.rany.cake.devops.base.api.query.WebhookBasicQuery;
import com.rany.cake.devops.base.api.query.WebhookPageQuery;

/**
 * webhook配置
 *
 * @author zhongshengwang
 * @description webhook配置
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface WebhookConfigService {

    /**
     * 创建
     *
     * @param command 创建
     * @return webhookId
     */
    String createWebhook(CreateWebhookCommand command);

    /**
     * 更新
     *
     * @param command 更新
     * @return success
     */
    Boolean modifyWebhook(ModifyWebHookCommand command);

    /**
     * 删除
     *
     * @param command 删除
     * @return success
     */
    Boolean deleteWebhook(DeleteWebhookCommand command);

    /**
     * 获取详情
     *
     * @param basicQuery 详情
     * @return 详情
     */
    WebHookConfigDTO getWebhook(WebhookBasicQuery basicQuery);


    /**
     * 分页查询列表
     *
     * @param pageQuery 查询
     * @return 列表
     */
    Page<WebHookConfigDTO> pageWebhook(WebhookPageQuery pageQuery);
}
