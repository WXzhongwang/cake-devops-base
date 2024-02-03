package com.rany.cake.devops.base.api.command.wehook;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 创建webhook
 *
 * @author zhongshengwang
 * @description 创建
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateWebhookCommand extends BaseCommand {
    private String webhookName;
    private String webhookUrl;
    private String webhookType;
    private String webhookConfig;
}
