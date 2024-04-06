package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.WebhookConfig;
import com.rany.cake.devops.base.domain.repository.param.WebhookConfigQueryParam;
import com.rany.cake.devops.base.infra.po.WebhookConfigPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WebhookConfigDao {

    /**
     * webhook保存
     *
     * @param webhookConfig webhook
     * @return 行数
     */
    int save(WebhookConfig webhookConfig);

    /**
     * webhook更新
     *
     * @param webhookConfig webhook
     * @return 行数
     */
    int update(WebhookConfig webhookConfig);

    List<WebhookConfigPO> selectByIds(@Param("ids") List<Long> ids);

    List<WebhookConfigPO> queryWebHookConfig(WebhookConfigQueryParam webhookConfigQueryParam);

}
