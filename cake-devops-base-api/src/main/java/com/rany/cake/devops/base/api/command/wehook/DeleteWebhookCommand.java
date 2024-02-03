package com.rany.cake.devops.base.api.command.wehook;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 删除机器代理
 *
 * @author zhongshengwang
 * @description 删除机器代理
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeleteWebhookCommand extends BaseCommand {

    private Long webhookId;

}
