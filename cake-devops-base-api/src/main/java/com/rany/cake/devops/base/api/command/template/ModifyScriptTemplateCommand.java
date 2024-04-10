package com.rany.cake.devops.base.api.command.template;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 编辑webhook
 *
 * @author zhongshengwang
 * @description 编辑webhook
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModifyScriptTemplateCommand extends BaseCommand {

    private Long id;

    private String templateName;
    private String templateValue;
    private String description;
}
