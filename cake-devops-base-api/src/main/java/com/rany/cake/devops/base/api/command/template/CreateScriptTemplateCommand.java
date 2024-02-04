package com.rany.cake.devops.base.api.command.template;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 创建 script
 *
 * @author zhongshengwang
 * @description 创建
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateScriptTemplateCommand extends BaseCommand {
    
    private String templateName;
    private String templateValue;
    private String description;
}
